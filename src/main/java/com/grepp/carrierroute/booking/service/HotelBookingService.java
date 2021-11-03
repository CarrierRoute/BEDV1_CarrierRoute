package com.grepp.carrierroute.booking.service;

import com.grepp.carrierroute.booking.domain.HotelBooking;
import com.grepp.carrierroute.booking.dto.HotelBookingDetailsDto;
import com.grepp.carrierroute.booking.dto.HotelBookingRequestDto;
import com.grepp.carrierroute.booking.dto.HotelBookingResponseDto;
import com.grepp.carrierroute.exception.booking.InsufficentRoomException;
import com.grepp.carrierroute.booking.repository.HotelBookingRepository;
import com.grepp.carrierroute.booking.service.converter.HotelBookingConverter;
import com.grepp.carrierroute.exception.NotFoundException;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.hotel.repository.HotelRoomRepository;
import com.grepp.carrierroute.user.domain.User;
import com.grepp.carrierroute.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelBookingService {
    private final UserRepository userRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final HotelBookingRepository hotelBookingRepository;
    private final HotelBookingConverter converter;

    public HotelBookingResponseDto bookRooms(HotelBookingRequestDto bookingRequestDto, String userId){
        User user = getUser(userId);
        List<HotelRoom> roomsToBook = getAvailableRooms(bookingRequestDto);
        long totalPrice = calculateTotalPrice(roomsToBook, bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate());

        List<HotelBooking> bookings = bookRooms(user, roomsToBook, totalPrice, bookingRequestDto);

        return converter.convertToHotelBookingResponseDto(totalPrice, bookings, bookingRequestDto);
    }

    public HotelBookingDetailsDto getHotelBooking(Long bookingId){
        HotelBooking hotelBooking = hotelBookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException(HotelBooking.class, bookingId));

        return converter.convertToHotelBookingDetailsDto(hotelBooking);
    }

    public List<HotelBookingDetailsDto> getHotelBookings(String userId){
        return hotelBookingRepository.findAllByUser(getUser(userId))
                .stream()
                .map(converter::convertToHotelBookingDetailsDto)
                .collect(Collectors.toList());
    }

    private User getUser(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(User.class, userId));
    }

    private List<HotelRoom> getAvailableRooms(HotelBookingRequestDto bookingRequestDto){
        int requestNumberOfRoom = bookingRequestDto.getNumOfRoom();

        List<HotelRoom> roomsToBook = bookingRequestDto.getCandidateIdListOfRoom()
                .stream()
                .filter(hotelRoomRepository::existsById)
                .filter(roomId ->!isRoomBooked(roomId, bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate()))
                .limit(requestNumberOfRoom)
                .map(hotelRoomRepository::getById)
                .collect(Collectors.toList());

        if(roomsToBook.size() < requestNumberOfRoom){
            throw new InsufficentRoomException();
        }

        return roomsToBook;
    }

    private boolean isRoomBooked(Long roomId, LocalDate checkInDate, LocalDate checkOutDate){
        return hotelBookingRepository.existsByHotelRoomAndDate(roomId, checkInDate, checkOutDate);
    }

    private List<HotelBooking> bookRooms(User user, List<HotelRoom> roomsToBook, long totalPrice, HotelBookingRequestDto bookingRequestDto){
        payPoints(user, totalPrice);

        return roomsToBook.stream()
                .map(room -> bookRoom(user, room, bookingRequestDto))
                .collect(Collectors.toList());
    }

    private HotelBooking bookRoom(User user, HotelRoom roomToBook, HotelBookingRequestDto bookingRequestDto){
        long price = calculatePrice(roomToBook, bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate());
        return hotelBookingRepository.save(converter.convertToHotelBooking(user, roomToBook, price, bookingRequestDto));
    }

    private void payPoints(User user, long totalPrice){
        user.subtractPoint(totalPrice);
    }

    private long calculateTotalPrice(List<HotelRoom> rooms, LocalDate checkInDate, LocalDate checkOutDate){
        return rooms.stream()
                .mapToLong(room -> room.getPricePerDay() * ChronoUnit.DAYS.between(checkInDate, checkOutDate))
                .sum();

    }

    private long calculatePrice(HotelRoom room, LocalDate checkInDate, LocalDate checkOutDate){
        return room.getPricePerDay() * ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }
}
