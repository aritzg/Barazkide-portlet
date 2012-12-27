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
import com.liferay.portal.model.CacheModel;

import net.sareweb.barazkide.model.GardenBadge;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing GardenBadge in entity cache.
 *
 * @author A.Galdos
 * @see GardenBadge
 * @generated
 */
public class GardenBadgeCacheModel implements CacheModel<GardenBadge>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{gardenBadgeId=");
		sb.append(gardenBadgeId);
		sb.append(", gardenId=");
		sb.append(gardenId);
		sb.append(", badgeId=");
		sb.append(badgeId);
		sb.append(", obtainedDate=");
		sb.append(obtainedDate);
		sb.append("}");

		return sb.toString();
	}

	public GardenBadge toEntityModel() {
		GardenBadgeImpl gardenBadgeImpl = new GardenBadgeImpl();

		gardenBadgeImpl.setGardenBadgeId(gardenBadgeId);
		gardenBadgeImpl.setGardenId(gardenId);
		gardenBadgeImpl.setBadgeId(badgeId);

		if (obtainedDate == Long.MIN_VALUE) {
			gardenBadgeImpl.setObtainedDate(null);
		}
		else {
			gardenBadgeImpl.setObtainedDate(new Date(obtainedDate));
		}

		gardenBadgeImpl.resetOriginalValues();

		return gardenBadgeImpl;
	}

	public long gardenBadgeId;
	public long gardenId;
	public long badgeId;
	public long obtainedDate;
}