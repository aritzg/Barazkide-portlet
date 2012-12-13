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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import net.sareweb.barazkide.model.GardenImage;
import net.sareweb.barazkide.service.EventLocalService;
import net.sareweb.barazkide.service.EventService;
import net.sareweb.barazkide.service.FollowingLocalService;
import net.sareweb.barazkide.service.FollowingService;
import net.sareweb.barazkide.service.GardenImageLocalService;
import net.sareweb.barazkide.service.GardenImageService;
import net.sareweb.barazkide.service.GardenLocalService;
import net.sareweb.barazkide.service.GardenService;
import net.sareweb.barazkide.service.MembershipLocalService;
import net.sareweb.barazkide.service.MembershipService;
import net.sareweb.barazkide.service.persistence.EventFinder;
import net.sareweb.barazkide.service.persistence.EventPersistence;
import net.sareweb.barazkide.service.persistence.FollowingPersistence;
import net.sareweb.barazkide.service.persistence.GardenFinder;
import net.sareweb.barazkide.service.persistence.GardenImagePersistence;
import net.sareweb.barazkide.service.persistence.GardenPersistence;
import net.sareweb.barazkide.service.persistence.MembershipFinder;
import net.sareweb.barazkide.service.persistence.MembershipPersistence;

import javax.sql.DataSource;

/**
 * The base implementation of the garden image remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link net.sareweb.barazkide.service.impl.GardenImageServiceImpl}.
 * </p>
 *
 * @author A.Galdos
 * @see net.sareweb.barazkide.service.impl.GardenImageServiceImpl
 * @see net.sareweb.barazkide.service.GardenImageServiceUtil
 * @generated
 */
public abstract class GardenImageServiceBaseImpl extends BaseServiceImpl
	implements GardenImageService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link net.sareweb.barazkide.service.GardenImageServiceUtil} to access the garden image remote service.
	 */

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
	}

	public void destroy() {
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
	private GardenImageServiceClpInvoker _clpInvoker = new GardenImageServiceClpInvoker();
}