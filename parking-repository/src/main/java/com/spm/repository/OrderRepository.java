package com.spm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spm.entity.OrderEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	OrderEntity findByOrderIdAndProjectId(Long orderId, Long projectId);

	@Query(value = "SELECT * from orders  WHERE  (project_id = :projectId) AND (:adminCheckInId is null OR admin_checkin_id = :adminCheckInId) AND (:adminCheckOutId is null OR admin_checkout_id = :adminCheckOutId) AND (:cardCode is null OR card_code = :cardCode)  AND  (:cardStt is null OR card_stt = :cardStt)  AND (:carNumber is null OR car_number like %:carNumber%) AND ((checkout_time >= :dateFrom AND checkout_time <= :dateTo) OR ((checkin_time >= :dateFrom AND checkin_time <= :dateTo) AND checkout_time = 0)) order by updated DESC", 
			countQuery = "SELECT * from orders  WHERE  (project_id = :projectId) AND (:adminCheckInId is null OR admin_checkin_id = :adminCheckInId) AND (:adminCheckOutId is null OR admin_checkout_id = :adminCheckOutId) AND (:cardCode is null OR card_code = :cardCode)  AND  (:cardStt is null OR card_stt = :cardStt)  AND (:carNumber is null OR car_number like %:carNumber%) AND ((checkout_time >= :dateFrom AND checkout_time <= :dateTo) OR ((checkin_time >= :dateFrom AND checkin_time <= :dateTo) AND checkout_time = 0)) order by updated DESC", nativeQuery = true)
	Page<OrderEntity> search(@Param(value = "projectId") String projectId,
			@Param(value="adminCheckInId") String adminCheckInId,
			@Param(value="adminCheckOutId") String adminCheckOutId,
			@Param(value = "cardCode") String cardCode, 
			@Param(value = "cardStt") String cardStt,
			@Param(value = "carNumber") String carNumber, @Param(value = "dateFrom") String dateFrom,
			@Param(value = "dateTo") String dateTo, Pageable pageable);

	@Query(value = "SELECT * from orders  WHERE  project_id = :projectId AND (:adminCheckInId is null OR admin_checkin_id = :adminCheckInId) AND ((checkout_time >= :dateFrom AND checkout_time <= :dateTo) OR ((checkin_time >= :dateFrom AND checkin_time <= :dateTo) AND (checkout_time = 0 OR checkout_time > :dateTo))) order by updated DESC", nativeQuery = true)
	List<OrderEntity> findAll(@Param(value = "projectId") String projectId,
			@Param(value = "adminCheckInId") String adminCheckInId, @Param(value = "dateFrom") String dateFrom,
			@Param(value = "dateTo") String dateTo);
	
	@Query(value = "SELECT * from orders  WHERE  (project_id = :projectId) AND (vehicle_id in (:verhicleIds)) AND (:cardCode is null OR card_code = :cardCode)  AND  (:cardStt is null OR card_stt = :cardStt)  AND (:carNumber is null OR car_number like %:carNumber%) AND ((checkout_time >= :dateFrom AND checkout_time <= :dateTo) OR ((checkin_time >= :dateFrom AND checkin_time <= :dateTo) AND checkout_time = 0)) order by updated DESC", 
			countQuery = "SELECT * from orders  WHERE  (project_id = :projectId) AND (vehicle_id in (:verhicleIds)) AND (:cardCode is null OR card_code = :cardCode)  AND  (:cardStt is null OR card_stt = :cardStt)  AND (:carNumber is null OR car_number like %:carNumber%) AND ((checkout_time >= :dateFrom AND checkout_time <= :dateTo) OR ((checkin_time >= :dateFrom AND checkin_time <= :dateTo) AND checkout_time = 0 )) order by updated DESC", nativeQuery = true)
	Page<OrderEntity> searchAllMonthlyCardInOut(@Param(value = "projectId") String projectId, @Param(value = "cardCode") String cardCode, @Param(value = "cardStt") String cardStt,
			@Param(value = "carNumber") String carNumber, @Param(value = "dateFrom") String dateFrom,
			@Param(value = "dateTo") String dateTo, @Param(value="verhicleIds")int[] verhicleIds, Pageable pageable);

	@Query(value = "SELECT * from orders  WHERE  project_id = :projectId AND (:adminCheckInId is null OR admin_checkin_id = :adminCheckInId) AND (checkin_time >= :dateFrom AND checkin_time <= :dateTo) order by updated DESC", nativeQuery = true)
	List<OrderEntity> findAllVehicleIn(@Param(value = "projectId") String projectId,
			@Param(value = "adminCheckInId") String adminCheckInId, @Param(value = "dateFrom") String dateFrom,
			@Param(value = "dateTo") String dateTo);
	
	@Query(value = "SELECT * from orders  WHERE  project_id = :projectId AND (:adminCheckInId is null OR admin_checkin_id = :adminCheckInId) AND (checkout_time >= :dateFrom AND checkout_time <= :dateTo) order by updated DESC", nativeQuery = true)
	List<OrderEntity> findAllVehicleOut(@Param(value = "projectId") String projectId,
			@Param(value = "adminCheckInId") String adminCheckInId, @Param(value = "dateFrom") String dateFrom,
			@Param(value = "dateTo") String dateTo);
	
	@Query(value = "SELECT * from orders  WHERE  project_id = :projectId AND (checkout_time = 0 || checkout_time is null ) order by updated DESC", nativeQuery = true)
	List<OrderEntity> findAllVehicleExistingInParking(@Param(value = "projectId") String projectId);
	
	@Query(value = "SELECT * from orders  WHERE  project_id = :projectId AND (checkout_time = 0 || checkout_time is null ) order by updated DESC", nativeQuery = true)
	Page<OrderEntity> findAllVehicleExistingInParking(@Param(value = "projectId") String projectId, Pageable pageable);
	
}
