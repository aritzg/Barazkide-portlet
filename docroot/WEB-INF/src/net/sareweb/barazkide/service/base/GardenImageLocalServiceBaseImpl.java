/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package net.sareweb.barazkide.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import net.sareweb.barazkide.model.GardenImage;
import net.sareweb.barazkide.service.BadgeLocalService;
import net.sareweb.barazkide.service.BadgeService;
import net.sareweb.barazkide.service.EventLocalService;
import net.sareweb.barazkide.service.EventService;
import net.sareweb.barazkide.service.FollowingLocalService;
import net.sareweb.barazkide.service.FollowingService;
import net.sareweb.barazkide.service.GardenBadgeLocalService;
import net.sareweb.barazkide.service.GardenImageLocalService;
import net.sareweb.barazkide.service.GardenImageService;
import net.sareweb.barazkide.service.GardenLocalService;
import net.sareweb.barazkide.service.GardenService;
import net.sareweb.barazkide.service.MembershipLocalService;
import net.sareweb.barazkide.service.MembershipService;
import net.sareweb.barazkide.service.RatingLocalService;
import net.sareweb.barazkide.service.RatingService;
import net.sareweb.barazkide.service.persistence.BadgeFinder;
import net.sareweb.barazkide.service.persistence.BadgePersistence;
import net.sareweb.barazkide.service.persistence.EventFinder;
import net.sareweb.barazkide.service.persistence.EventPersistence;
import net.sareweb.barazkide.service.persistence.FollowingPersistence;
import net.sareweb.barazkide.service.persistence.GardenBadgePersistence;
import net.sareweb.barazkide.service.persistence.GardenFinder;
import net.sareweb.barazkide.service.persistence.GardenImagePersistence;
import net.sareweb.barazkide.service.persistence.GardenPersistence;
import net.sareweb.barazkide.service.persistence.MembershipFinder;
import net.sareweb.barazkide.service.persistence.MembershipPersistence;
import net.sareweb.barazkide.service.persistence.RatingFinder;
import net.sareweb.barazkide.service.persistence.RatingPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the garden image local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link net.sareweb.barazkide.service.impl.GardenImageLocalServiceImpl}.
 * </p>
 *
 * @author A.Galdos
 * @see net.sareweb.barazkide.service.impl.GardenImageLocalServiceImpl
 * @see net.sareweb.barazkide.service.GardenImageLocalServiceUtil
 * @generated
 */
public abstract class GardenImageLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements GardenImageLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link net.sareweb.barazkide.service.GardenImageLocalServiceUtil} to access the garden image local service.
	 */

	/**
	 * Adds the garden image to the database. Also notifies the appropriate model listeners.
	 *
	 * @param gardenImage the garden image
	 * @return the garden image that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public GardenImage addGardenImage(GardenImage gardenImage)
		throws SystemException {
		gardenImage.setNew(true);

		return gardenImagePersistence.update(gardenImage, false);
	}

	/**
	 * Creates a new garden image with the primary key. Does not add the garden image to the database.
	 *
	 * @param gardenImageId the primary key for the new garden image
	 * @return the new garden image
	 */
	public GardenImage createGardenImage(long gardenImageId) {
		return gardenImagePersistence.create(gardenImageId);
	}

	/**
	 * Deletes the garden image with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gardenImageId the primary key of the garden image
	 * @return the garden image that was removed
	 * @throws PortalException if a garden image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public GardenImage deleteGardenImage(long gardenImageId)
		throws PortalException, SystemException {
		return gardenImagePersistence.remove(gardenImageId);
	}

	/**
	 * Deletes the garden image from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gardenImage the garden image
	 * @return the garden image that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public GardenImage deleteGardenImage(GardenImage gardenImage)
		throws SystemException {
		return gardenImagePersistence.remove(gardenImage);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(GardenImage.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return gardenImagePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return gardenImagePersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return gardenImagePersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return gardenImagePersistence.countWithDynamicQuery(dynamicQuery);
	}

	public GardenImage fetchGardenImage(long gardenImageId)
		throws SystemException {
		return gardenImagePersistence.fetchByPrimaryKey(gardenImageId);
	}

	/**
	 * Returns the garden image with the primary key.
	 *
	 * @param gardenImageId the primary key of the garden image
	 * @return the garden image
	 * @throws PortalException if a garden image with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public GardenImage getGardenImage(long gardenImageId)
		throws PortalException, SystemException {
		return gardenImagePersistence.findByPrimaryKey(gardenImageId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return gardenImagePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the garden images.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of garden images
	 * @param end the upper bound of the range of garden images (not inclusive)
	 * @return the range of garden images
	 * @throws SystemException if a system exception occurred
	 */
	public List<GardenImage> getGardenImages(int start, int end)
		throws SystemException {
		return gardenImagePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of garden images.
	 *
	 * @return the number of garden images
	 * @throws SystemException if a system exception occurred
	 */
	public int getGardenImagesCount() throws SystemException {
		return gardenImagePersistence.countAll();
	}

	/**
	 * Updates the garden image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param gardenImage the garden image
	 * @return the garden image that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public GardenImage updateGardenImage(GardenImage gardenImage)
		throws SystemException {
		return updateGardenImage(gardenImage, true);
	}

	/**
	 * Updates the garden image in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param gardenImage the garden image
	 * @param merge whether to merge the garden image with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the garden image that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public GardenImage updateGardenImage(GardenImage gardenImage, boolean merge)
		throws SystemException {
		gardenImage.setNew(false);

		return gardenImagePersistence.update(gardenImage, merge);
	}

	/**
	 * Returns the badge local service.
	 *
	 * @return the badge local service
	 */
	public BadgeLocalService getBadgeLocalService() {
		return badgeLocalService;
	}

	/**
	 * Sets the badge local service.
	 *
	 * @param badgeLocalService the badge local service
	 */
	public void setBadgeLocalService(BadgeLocalService badgeLocalService) {
		this.badgeLocalService = badgeLocalService;
	}

	/**
	 * Returns the badge remote service.
	 *
	 * @return the badge remote service
	 */
	public BadgeService getBadgeService() {
		return badgeService;
	}

	/**
	 * Sets the badge remote service.
	 *
	 * @param badgeService the badge remote service
	 */
	public void setBadgeService(BadgeService badgeService) {
		this.badgeService = badgeService;
	}

	/**
	 * Returns the badge persistence.
	 *
	 * @return the badge persistence
	 */
	public BadgePersistence getBadgePersistence() {
		return badgePersistence;
	}

	/**
	 * Sets the badge persistence.
	 *
	 * @param badgePersistence the badge persistence
	 */
	public void setBadgePersistence(BadgePersistence badgePersistence) {
		this.badgePersistence = badgePersistence;
	}

	/**
	 * Returns the badge finder.
	 *
	 * @return the badge finder
	 */
	public BadgeFinder getBadgeFinder() {
		return badgeFinder;
	}

	/**
	 * Sets the badge finder.
	 *
	 * @param badgeFinder the badge finder
	 */
	public void setBadgeFinder(BadgeFinder badgeFinder) {
		this.badgeFinder = badgeFinder;
	}

	/**
	 * Returns the event local service.
	 *
	 * @return the event local service
	 */
	public EventLocalService getEventLocalService() {
		return eventLocalService;
	}

	/**
	 * Sets the event local service.
	 *
	 * @param eventLocalService the event local service
	 */
	public void setEventLocalService(EventLocalService eventLocalService) {
		this.eventLocalService = eventLocalService;
	}

	/**
	 * Returns the event remote service.
	 *
	 * @return the event remote service
	 */
	public EventService getEventService() {
		return eventService;
	}

	/**
	 * Sets the event remote service.
	 *
	 * @param eventService the event remote service
	 */
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	/**
	 * Returns the event persistence.
	 *
	 * @return the event persistence
	 */
	public EventPersistence getEventPersistence() {
		return eventPersistence;
	}

	/**
	 * Sets the event persistence.
	 *
	 * @param eventPersistence the event persistence
	 */
	public void setEventPersistence(EventPersistence eventPersistence) {
		this.eventPersistence = eventPersistence;
	}

	/**
	 * Returns the event finder.
	 *
	 * @return the event finder
	 */
	public EventFinder getEventFinder() {
		return eventFinder;
	}

	/**
	 * Sets the event finder.
	 *
	 * @param eventFinder the event finder
	 */
	public void setEventFinder(EventFinder eventFinder) {
		this.eventFinder = eventFinder;
	}

	/**
	 * Returns the following local service.
	 *
	 * @return the following local service
	 */
	public FollowingLocalService getFollowingLocalService() {
		return followingLocalService;
	}

	/**
	 * Sets the following local service.
	 *
	 * @param followingLocalService the following local service
	 */
	public void setFollowingLocalService(
		FollowingLocalService followingLocalService) {
		this.followingLocalService = followingLocalService;
	}

	/**
	 * Returns the following remote service.
	 *
	 * @return the following remote service
	 */
	public FollowingService getFollowingService() {
		return followingService;
	}

	/**
	 * Sets the following remote service.
	 *
	 * @param followingService the following remote service
	 */
	public void setFollowingService(FollowingService followingService) {
		this.followingService = followingService;
	}

	/**
	 * Returns the following persistence.
	 *
	 * @return the following persistence
	 */
	public FollowingPersistence getFollowingPersistence() {
		return followingPersistence;
	}

	/**
	 * Sets the following persistence.
	 *
	 * @param followingPersistence the following persistence
	 */
	public void setFollowingPersistence(
		FollowingPersistence followingPersistence) {
		this.followingPersistence = followingPersistence;
	}

	/**
	 * Returns the garden local service.
	 *
	 * @return the garden local service
	 */
	public GardenLocalService getGardenLocalService() {
		return gardenLocalService;
	}

	/**
	 * Sets the garden local service.
	 *
	 * @param gardenLocalService the garden local service
	 */
	public void setGardenLocalService(GardenLocalService gardenLocalService) {
		this.gardenLocalService = gardenLocalService;
	}

	/**
	 * Returns the garden remote service.
	 *
	 * @return the garden remote service
	 */
	public GardenService getGardenService() {
		return gardenService;
	}

	/**
	 * Sets the garden remote service.
	 *
	 * @param gardenService the garden remote service
	 */
	public void setGardenService(GardenService gardenService) {
		this.gardenService = gardenService;
	}

	/**
	 * Returns the garden persistence.
	 *
	 * @return the garden persistence
	 */
	public GardenPersistence getGardenPersistence() {
		return gardenPersistence;
	}

	/**
	 * Sets the garden persistence.
	 *
	 * @param gardenPersistence the garden persistence
	 */
	public void setGardenPersistence(GardenPersistence gardenPersistence) {
		this.gardenPersistence = gardenPersistence;
	}

	/**
	 * Returns the garden finder.
	 *
	 * @return the garden finder
	 */
	public GardenFinder getGardenFinder() {
		return gardenFinder;
	}

	/**
	 * Sets the garden finder.
	 *
	 * @param gardenFinder the garden finder
	 */
	public void setGardenFinder(GardenFinder gardenFinder) {
		this.gardenFinder = gardenFinder;
	}

	/**
	 * Returns the garden badge local service.
	 *
	 * @return the garden badge local service
	 */
	public GardenBadgeLocalService getGardenBadgeLocalService() {
		return gardenBadgeLocalService;
	}

	/**
	 * Sets the garden badge local service.
	 *
	 * @param gardenBadgeLocalService the garden badge local service
	 */
	public void setGardenBadgeLocalService(
		GardenBadgeLocalService gardenBadgeLocalService) {
		this.gardenBadgeLocalService = gardenBadgeLocalService;
	}

	/**
	 * Returns the garden badge persistence.
	 *
	 * @return the garden badge persistence
	 */
	public GardenBadgePersistence getGardenBadgePersistence() {
		return gardenBadgePersistence;
	}

	/**
	 * Sets the garden badge persistence.
	 *
	 * @param gardenBadgePersistence the garden badge persistence
	 */
	public void setGardenBadgePersistence(
		GardenBadgePersistence gardenBadgePersistence) {
		this.gardenBadgePersistence = gardenBadgePersistence;
	}

	/**
	 * Returns the garden image local service.
	 *
	 * @return the garden image local service
	 */
	public GardenImageLocalService getGardenImageLocalService() {
		return gardenImageLocalService;
	}

	/**
	 * Sets the garden image local service.
	 *
	 * @param gardenImageLocalService the garden image local service
	 */
	public void setGardenImageLocalService(
		GardenImageLocalService gardenImageLocalService) {
		this.gardenImageLocalService = gardenImageLocalService;
	}

	/**
	 * Returns the garden image remote service.
	 *
	 * @return the garden image remote service
	 */
	public GardenImageService getGardenImageService() {
		return gardenImageService;
	}

	/**
	 * Sets the garden image remote service.
	 *
	 * @param gardenImageService the garden image remote service
	 */
	public void setGardenImageService(GardenImageService gardenImageService) {
		this.gardenImageService = gardenImageService;
	}

	/**
	 * Returns the garden image persistence.
	 *
	 * @return the garden image persistence
	 */
	public GardenImagePersistence getGardenImagePersistence() {
		return gardenImagePersistence;
	}

	/**
	 * Sets the garden image persistence.
	 *
	 * @param gardenImagePersistence the garden image persistence
	 */
	public void setGardenImagePersistence(
		GardenImagePersistence gardenImagePersistence) {
		this.gardenImagePersistence = gardenImagePersistence;
	}

	/**
	 * Returns the membership local service.
	 *
	 * @return the membership local service
	 */
	public MembershipLocalService getMembershipLocalService() {
		return membershipLocalService;
	}

	/**
	 * Sets the membership local service.
	 *
	 * @param membershipLocalService the membership local service
	 */
	public void setMembershipLocalService(
		MembershipLocalService membershipLocalService) {
		this.membershipLocalService = membershipLocalService;
	}

	/**
	 * Returns the membership remote service.
	 *
	 * @return the membership remote service
	 */
	public MembershipService getMembershipService() {
		return membershipService;
	}

	/**
	 * Sets the membership remote service.
	 *
	 * @param membershipService the membership remote service
	 */
	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	/**
	 * Returns the membership persistence.
	 *
	 * @return the membership persistence
	 */
	public MembershipPersistence getMembershipPersistence() {
		return membershipPersistence;
	}

	/**
	 * Sets the membership persistence.
	 *
	 * @param membershipPersistence the membership persistence
	 */
	public void setMembershipPersistence(
		MembershipPersistence membershipPersistence) {
		this.membershipPersistence = membershipPersistence;
	}

	/**
	 * Returns the membership finder.
	 *
	 * @return the membership finder
	 */
	public MembershipFinder getMembershipFinder() {
		return membershipFinder;
	}

	/**
	 * Sets the membership finder.
	 *
	 * @param membershipFinder the membership finder
	 */
	public void setMembershipFinder(MembershipFinder membershipFinder) {
		this.membershipFinder = membershipFinder;
	}

	/**
	 * Returns the rating local service.
	 *
	 * @return the rating local service
	 */
	public RatingLocalService getRatingLocalService() {
		return ratingLocalService;
	}

	/**
	 * Sets the rating local service.
	 *
	 * @param ratingLocalService the rating local service
	 */
	public void setRatingLocalService(RatingLocalService ratingLocalService) {
		this.ratingLocalService = ratingLocalService;
	}

	/**
	 * Returns the rating remote service.
	 *
	 * @return the rating remote service
	 */
	public RatingService getRatingService() {
		return ratingService;
	}

	/**
	 * Sets the rating remote service.
	 *
	 * @param ratingService the rating remote service
	 */
	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	/**
	 * Returns the rating persistence.
	 *
	 * @return the rating persistence
	 */
	public RatingPersistence getRatingPersistence() {
		return ratingPersistence;
	}

	/**
	 * Sets the rating persistence.
	 *
	 * @param ratingPersistence the rating persistence
	 */
	public void setRatingPersistence(RatingPersistence ratingPersistence) {
		this.ratingPersistence = ratingPersistence;
	}

	/**
	 * Returns the rating finder.
	 *
	 * @return the rating finder
	 */
	public RatingFinder getRatingFinder() {
		return ratingFinder;
	}

	/**
	 * Sets the rating finder.
	 *
	 * @param ratingFinder the rating finder
	 */
	public void setRatingFinder(RatingFinder ratingFinder) {
		this.ratingFinder = ratingFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		PersistedModelLocalServiceRegistryUtil.register("net.sareweb.barazkide.model.GardenImage",
			gardenImageLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"net.sareweb.barazkide.model.GardenImage");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
	}

	protected Class<?> getModelClass() {
		return GardenImage.class;
	}

	protected String getModelClassName() {
		return GardenImage.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = gardenImagePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = BadgeLocalService.class)
	protected BadgeLocalService badgeLocalService;
	@BeanReference(type = BadgeService.class)
	protected BadgeService badgeService;
	@BeanReference(type = BadgePersistence.class)
	protected BadgePersistence badgePersistence;
	@BeanReference(type = BadgeFinder.class)
	protected BadgeFinder badgeFinder;
	@BeanReference(type = EventLocalService.class)
	protected EventLocalService eventLocalService;
	@BeanReference(type = EventService.class)
	protected EventService eventService;
	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;
	@BeanReference(type = EventFinder.class)
	protected EventFinder eventFinder;
	@BeanReference(type = FollowingLocalService.class)
	protected FollowingLocalService followingLocalService;
	@BeanReference(type = FollowingService.class)
	protected FollowingService followingService;
	@BeanReference(type = FollowingPersistence.class)
	protected FollowingPersistence followingPersistence;
	@BeanReference(type = GardenLocalService.class)
	protected GardenLocalService gardenLocalService;
	@BeanReference(type = GardenService.class)
	protected GardenService gardenService;
	@BeanReference(type = GardenPersistence.class)
	protected GardenPersistence gardenPersistence;
	@BeanReference(type = GardenFinder.class)
	protected GardenFinder gardenFinder;
	@BeanReference(type = GardenBadgeLocalService.class)
	protected GardenBadgeLocalService gardenBadgeLocalService;
	@BeanReference(type = GardenBadgePersistence.class)
	protected GardenBadgePersistence gardenBadgePersistence;
	@BeanReference(type = GardenImageLocalService.class)
	protected GardenImageLocalService gardenImageLocalService;
	@BeanReference(type = GardenImageService.class)
	protected GardenImageService gardenImageService;
	@BeanReference(type = GardenImagePersistence.class)
	protected GardenImagePersistence gardenImagePersistence;
	@BeanReference(type = MembershipLocalService.class)
	protected MembershipLocalService membershipLocalService;
	@BeanReference(type = MembershipService.class)
	protected MembershipService membershipService;
	@BeanReference(type = MembershipPersistence.class)
	protected MembershipPersistence membershipPersistence;
	@BeanReference(type = MembershipFinder.class)
	protected MembershipFinder membershipFinder;
	@BeanReference(type = RatingLocalService.class)
	protected RatingLocalService ratingLocalService;
	@BeanReference(type = RatingService.class)
	protected RatingService ratingService;
	@BeanReference(type = RatingPersistence.class)
	protected RatingPersistence ratingPersistence;
	@BeanReference(type = RatingFinder.class)
	protected RatingFinder ratingFinder;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private GardenImageLocalServiceClpInvoker _clpInvoker = new GardenImageLocalServiceClpInvoker();
}