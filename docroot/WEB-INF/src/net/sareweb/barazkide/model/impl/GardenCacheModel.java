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

import net.sareweb.barazkide.model.Garden;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Garden in entity cache.
 *
 * @author A.Galdos
 * @see Garden
 * @generated
 */
public class GardenCacheModel implements CacheModel<Garden>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{gardenId=");
		sb.append(gardenId);
		sb.append(", gardenImageId=");
		sb.append(gardenImageId);
		sb.append(", ownerUserId=");
		sb.append(ownerUserId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", lat=");
		sb.append(lat);
		sb.append(", lng=");
		sb.append(lng);
		sb.append("}");

		return sb.toString();
	}

	public Garden toEntityModel() {
		GardenImpl gardenImpl = new GardenImpl();

		gardenImpl.setGardenId(gardenId);
		gardenImpl.setGardenImageId(gardenImageId);
		gardenImpl.setOwnerUserId(ownerUserId);

		if (createDate == Long.MIN_VALUE) {
			gardenImpl.setCreateDate(null);
		}
		else {
			gardenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			gardenImpl.setModifiedDate(null);
		}
		else {
			gardenImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			gardenImpl.setName(StringPool.BLANK);
		}
		else {
			gardenImpl.setName(name);
		}

		if (comment == null) {
			gardenImpl.setComment(StringPool.BLANK);
		}
		else {
			gardenImpl.setComment(comment);
		}

		gardenImpl.setLat(lat);
		gardenImpl.setLng(lng);

		gardenImpl.resetOriginalValues();

		return gardenImpl;
	}

	public long gardenId;
	public long gardenImageId;
	public long ownerUserId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String comment;
	public double lat;
	public double lng;
}