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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import net.sareweb.barazkide.model.Event;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Event in entity cache.
 *
 * @author A.Galdos
 * @see Event
 * @generated
 */
public class EventCacheModel implements CacheModel<Event>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{eventId=");
		sb.append(eventId);
		sb.append(", gardenId=");
		sb.append(gardenId);
		sb.append(", creatorUserId=");
		sb.append(creatorUserId);
		sb.append(", destinationUserId=");
		sb.append(destinationUserId);
		sb.append(", gardenImageId=");
		sb.append(gardenImageId);
		sb.append(", imageTitle=");
		sb.append(imageTitle);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append(", eventText=");
		sb.append(eventText);
		sb.append("}");

		return sb.toString();
	}

	public Event toEntityModel() {
		EventImpl eventImpl = new EventImpl();

		eventImpl.setEventId(eventId);
		eventImpl.setGardenId(gardenId);
		eventImpl.setCreatorUserId(creatorUserId);
		eventImpl.setDestinationUserId(destinationUserId);
		eventImpl.setGardenImageId(gardenImageId);

		if (imageTitle == null) {
			eventImpl.setImageTitle(StringPool.BLANK);
		}
		else {
			eventImpl.setImageTitle(imageTitle);
		}

		if (createDate == Long.MIN_VALUE) {
			eventImpl.setCreateDate(null);
		}
		else {
			eventImpl.setCreateDate(new Date(createDate));
		}

		if (eventType == null) {
			eventImpl.setEventType(StringPool.BLANK);
		}
		else {
			eventImpl.setEventType(eventType);
		}

		if (eventText == null) {
			eventImpl.setEventText(StringPool.BLANK);
		}
		else {
			eventImpl.setEventText(eventText);
		}

		eventImpl.resetOriginalValues();

		return eventImpl;
	}

	public long eventId;
	public long gardenId;
	public long creatorUserId;
	public long destinationUserId;
	public long gardenImageId;
	public String imageTitle;
	public long createDate;
	public String eventType;
	public String eventText;
}