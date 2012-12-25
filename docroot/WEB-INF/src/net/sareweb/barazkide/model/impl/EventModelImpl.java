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

package net.sareweb.barazkide.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import net.sareweb.barazkide.model.Event;
import net.sareweb.barazkide.model.EventModel;
import net.sareweb.barazkide.model.EventSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Event service. Represents a row in the &quot;Barazkide_Event&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link net.sareweb.barazkide.model.EventModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EventImpl}.
 * </p>
 *
 * @author A.Galdos
 * @see EventImpl
 * @see net.sareweb.barazkide.model.Event
 * @see net.sareweb.barazkide.model.EventModel
 * @generated
 */
@JSON(strict = true)
public class EventModelImpl extends BaseModelImpl<Event> implements EventModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a event model instance should use the {@link net.sareweb.barazkide.model.Event} interface instead.
	 */
	public static final String TABLE_NAME = "Barazkide_Event";
	public static final Object[][] TABLE_COLUMNS = {
			{ "eventId", Types.BIGINT },
			{ "gardenId", Types.BIGINT },
			{ "creatorUserId", Types.BIGINT },
			{ "destinationUserId", Types.BIGINT },
			{ "folderId", Types.BIGINT },
			{ "imageTitle", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "eventType", Types.VARCHAR },
			{ "eventText", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table Barazkide_Event (eventId LONG not null primary key,gardenId LONG,creatorUserId LONG,destinationUserId LONG,folderId LONG,imageTitle VARCHAR(75) null,createDate DATE null,eventType VARCHAR(75) null,eventText VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Barazkide_Event";
	public static final String ORDER_BY_JPQL = " ORDER BY event.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY Barazkide_Event.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.net.sareweb.barazkide.model.Event"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.net.sareweb.barazkide.model.Event"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.net.sareweb.barazkide.model.Event"),
			true);
	public static long GARDENID_COLUMN_BITMASK = 1L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Event toModel(EventSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Event model = new EventImpl();

		model.setEventId(soapModel.getEventId());
		model.setGardenId(soapModel.getGardenId());
		model.setCreatorUserId(soapModel.getCreatorUserId());
		model.setDestinationUserId(soapModel.getDestinationUserId());
		model.setFolderId(soapModel.getFolderId());
		model.setImageTitle(soapModel.getImageTitle());
		model.setCreateDate(soapModel.getCreateDate());
		model.setEventType(soapModel.getEventType());
		model.setEventText(soapModel.getEventText());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Event> toModels(EventSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Event> models = new ArrayList<Event>(soapModels.length);

		for (EventSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.net.sareweb.barazkide.model.Event"));

	public EventModelImpl() {
	}

	public long getPrimaryKey() {
		return _eventId;
	}

	public void setPrimaryKey(long primaryKey) {
		setEventId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_eventId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Event.class;
	}

	public String getModelClassName() {
		return Event.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventId", getEventId());
		attributes.put("gardenId", getGardenId());
		attributes.put("creatorUserId", getCreatorUserId());
		attributes.put("destinationUserId", getDestinationUserId());
		attributes.put("folderId", getFolderId());
		attributes.put("imageTitle", getImageTitle());
		attributes.put("createDate", getCreateDate());
		attributes.put("eventType", getEventType());
		attributes.put("eventText", getEventText());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long gardenId = (Long)attributes.get("gardenId");

		if (gardenId != null) {
			setGardenId(gardenId);
		}

		Long creatorUserId = (Long)attributes.get("creatorUserId");

		if (creatorUserId != null) {
			setCreatorUserId(creatorUserId);
		}

		Long destinationUserId = (Long)attributes.get("destinationUserId");

		if (destinationUserId != null) {
			setDestinationUserId(destinationUserId);
		}

		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
		}

		String imageTitle = (String)attributes.get("imageTitle");

		if (imageTitle != null) {
			setImageTitle(imageTitle);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		String eventText = (String)attributes.get("eventText");

		if (eventText != null) {
			setEventText(eventText);
		}
	}

	@JSON
	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	@JSON
	public long getGardenId() {
		return _gardenId;
	}

	public void setGardenId(long gardenId) {
		_columnBitmask |= GARDENID_COLUMN_BITMASK;

		if (!_setOriginalGardenId) {
			_setOriginalGardenId = true;

			_originalGardenId = _gardenId;
		}

		_gardenId = gardenId;
	}

	public long getOriginalGardenId() {
		return _originalGardenId;
	}

	@JSON
	public long getCreatorUserId() {
		return _creatorUserId;
	}

	public void setCreatorUserId(long creatorUserId) {
		_creatorUserId = creatorUserId;
	}

	public String getCreatorUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getCreatorUserId(), "uuid",
			_creatorUserUuid);
	}

	public void setCreatorUserUuid(String creatorUserUuid) {
		_creatorUserUuid = creatorUserUuid;
	}

	@JSON
	public long getDestinationUserId() {
		return _destinationUserId;
	}

	public void setDestinationUserId(long destinationUserId) {
		_destinationUserId = destinationUserId;
	}

	public String getDestinationUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getDestinationUserId(), "uuid",
			_destinationUserUuid);
	}

	public void setDestinationUserUuid(String destinationUserUuid) {
		_destinationUserUuid = destinationUserUuid;
	}

	@JSON
	public long getFolderId() {
		return _folderId;
	}

	public void setFolderId(long folderId) {
		_folderId = folderId;
	}

	@JSON
	public String getImageTitle() {
		if (_imageTitle == null) {
			return StringPool.BLANK;
		}
		else {
			return _imageTitle;
		}
	}

	public void setImageTitle(String imageTitle) {
		_imageTitle = imageTitle;
	}

	@JSON
	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	public String getEventType() {
		if (_eventType == null) {
			return StringPool.BLANK;
		}
		else {
			return _eventType;
		}
	}

	public void setEventType(String eventType) {
		_eventType = eventType;
	}

	@JSON
	public String getEventText() {
		if (_eventText == null) {
			return StringPool.BLANK;
		}
		else {
			return _eventText;
		}
	}

	public void setEventText(String eventText) {
		_eventText = eventText;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Event.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Event toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (Event)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		EventImpl eventImpl = new EventImpl();

		eventImpl.setEventId(getEventId());
		eventImpl.setGardenId(getGardenId());
		eventImpl.setCreatorUserId(getCreatorUserId());
		eventImpl.setDestinationUserId(getDestinationUserId());
		eventImpl.setFolderId(getFolderId());
		eventImpl.setImageTitle(getImageTitle());
		eventImpl.setCreateDate(getCreateDate());
		eventImpl.setEventType(getEventType());
		eventImpl.setEventText(getEventText());

		eventImpl.resetOriginalValues();

		return eventImpl;
	}

	public int compareTo(Event event) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), event.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Event event = null;

		try {
			event = (Event)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = event.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		EventModelImpl eventModelImpl = this;

		eventModelImpl._originalGardenId = eventModelImpl._gardenId;

		eventModelImpl._setOriginalGardenId = false;

		eventModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Event> toCacheModel() {
		EventCacheModel eventCacheModel = new EventCacheModel();

		eventCacheModel.eventId = getEventId();

		eventCacheModel.gardenId = getGardenId();

		eventCacheModel.creatorUserId = getCreatorUserId();

		eventCacheModel.destinationUserId = getDestinationUserId();

		eventCacheModel.folderId = getFolderId();

		eventCacheModel.imageTitle = getImageTitle();

		String imageTitle = eventCacheModel.imageTitle;

		if ((imageTitle != null) && (imageTitle.length() == 0)) {
			eventCacheModel.imageTitle = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			eventCacheModel.createDate = createDate.getTime();
		}
		else {
			eventCacheModel.createDate = Long.MIN_VALUE;
		}

		eventCacheModel.eventType = getEventType();

		String eventType = eventCacheModel.eventType;

		if ((eventType != null) && (eventType.length() == 0)) {
			eventCacheModel.eventType = null;
		}

		eventCacheModel.eventText = getEventText();

		String eventText = eventCacheModel.eventText;

		if ((eventText != null) && (eventText.length() == 0)) {
			eventCacheModel.eventText = null;
		}

		return eventCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{eventId=");
		sb.append(getEventId());
		sb.append(", gardenId=");
		sb.append(getGardenId());
		sb.append(", creatorUserId=");
		sb.append(getCreatorUserId());
		sb.append(", destinationUserId=");
		sb.append(getDestinationUserId());
		sb.append(", folderId=");
		sb.append(getFolderId());
		sb.append(", imageTitle=");
		sb.append(getImageTitle());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", eventType=");
		sb.append(getEventType());
		sb.append(", eventText=");
		sb.append(getEventText());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("net.sareweb.barazkide.model.Event");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>eventId</column-name><column-value><![CDATA[");
		sb.append(getEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gardenId</column-name><column-value><![CDATA[");
		sb.append(getGardenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>creatorUserId</column-name><column-value><![CDATA[");
		sb.append(getCreatorUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>destinationUserId</column-name><column-value><![CDATA[");
		sb.append(getDestinationUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>folderId</column-name><column-value><![CDATA[");
		sb.append(getFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>imageTitle</column-name><column-value><![CDATA[");
		sb.append(getImageTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>eventType</column-name><column-value><![CDATA[");
		sb.append(getEventType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>eventText</column-name><column-value><![CDATA[");
		sb.append(getEventText());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Event.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Event.class
		};
	private long _eventId;
	private long _gardenId;
	private long _originalGardenId;
	private boolean _setOriginalGardenId;
	private long _creatorUserId;
	private String _creatorUserUuid;
	private long _destinationUserId;
	private String _destinationUserUuid;
	private long _folderId;
	private String _imageTitle;
	private Date _createDate;
	private String _eventType;
	private String _eventText;
	private long _columnBitmask;
	private Event _escapedModelProxy;
}