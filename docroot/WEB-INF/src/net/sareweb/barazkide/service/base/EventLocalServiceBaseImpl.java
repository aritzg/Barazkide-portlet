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

import net.sareweb.barazkide.model.Event;
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

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the event local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link net.sareweb.barazkide.service.impl.EventLocalServiceImpl}.
 * </p>
 *
 * @author A.Galdos
 * @see net.sareweb.barazkide.service.impl.EventLocalServiceImpl
 * @see net.sareweb.barazkide.service.EventLocalServiceUtil
 * @generated
 */
public abstract class EventLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements EventLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link net.sareweb.barazkide.service.EventLocalServiceUtil} to access the event local service.
	 */

	/**
	 * Adds the event to the database. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Event addEvent(Event event) throws SystemException {
		event.setNew(true);

		return eventPersistence.update(event, false);
	}

	/**
	 * Creates a new event with the primary key. Does not add the event to the database.
	 *
	 * @param eventId the primary key for the new event
	 * @return the new event
	 */
	public Event createEvent(long eventId) {
		return eventPersistence.create(eventId);
	}

	/**
	 * Deletes the event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the event
	 * @return the event that was removed
	 * @throws PortalException if a event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public Event deleteEvent(long eventId)
		throws PortalException, SystemException {
		return eventPersistence.remove(eventId);
	}

	/**
	 * Deletes the event from the database. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public Event deleteEvent(Event event) throws SystemException {
		return eventPersistence.remove(event);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Event.class,
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
		return eventPersistence.findWithDynamicQuery(dynamicQuery);
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
		return eventPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
		return eventPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return eventPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Event fetchEvent(long eventId) throws SystemException {
		return eventPersistence.fetchByPrimaryKey(eventId);
	}

	/**
	 * Returns the event with the primary key.
	 *
	 * @param eventId the primary key of the event
	 * @return the event
	 * @throws PortalException if a event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Event getEvent(long eventId) throws PortalException, SystemException {
		return eventPersistence.findByPrimaryKey(eventId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return eventPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of events
	 * @param end the upper bound of the range of events (not inclusive)
	 * @return the range of events
	 * @throws SystemException if a system exception occurred
	 */
	public List<Event> getEvents(int start, int end) throws SystemException {
		return eventPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of events.
	 *
	 * @return the number of events
	 * @throws SystemException if a system exception occurred
	 */
	public int getEventsCount() throws SystemException {
		return eventPersistence.countAll();
	}

	/**
	 * Updates the event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @return the event that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Event updateEvent(Event event) throws SystemException {
		return updateEvent(event, true);
	}

	/**
	 * Updates the event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param event the event
	 * @param merge whether to merge the event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the event that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Event updateEvent(Event event, boolean merge)
		throws SystemException {
		event.setNew(false);

		return eventPersistence.update(event, merge);
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
		PersistedModelLocalServiceRegistryUtil.register("net.sareweb.barazkide.model.Event",
			eventLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"net.sareweb.barazkide.model.Event");
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
		return Event.class;
	}

	protected String getModelClassName() {
		return Event.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = eventPersistence.getDataSource();

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
	private EventLocalServiceClpInvoker _clpInvoker = new EventLocalServiceClpInvoker();
}