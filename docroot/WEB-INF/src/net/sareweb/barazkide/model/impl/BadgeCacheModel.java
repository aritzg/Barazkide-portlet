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

import net.sareweb.barazkide.model.Badge;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Badge in entity cache.
 *
 * @author A.Galdos
 * @see Badge
 * @generated
 */
public class BadgeCacheModel implements CacheModel<Badge>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{badgeId=");
		sb.append(badgeId);
		sb.append(", badgeType=");
		sb.append(badgeType);
		sb.append(", badgeTextKey=");
		sb.append(badgeTextKey);
		sb.append(", badgeImageUrl=");
		sb.append(badgeImageUrl);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append("}");

		return sb.toString();
	}

	public Badge toEntityModel() {
		BadgeImpl badgeImpl = new BadgeImpl();

		badgeImpl.setBadgeId(badgeId);

		if (badgeType == null) {
			badgeImpl.setBadgeType(StringPool.BLANK);
		}
		else {
			badgeImpl.setBadgeType(badgeType);
		}

		if (badgeTextKey == null) {
			badgeImpl.setBadgeTextKey(StringPool.BLANK);
		}
		else {
			badgeImpl.setBadgeTextKey(badgeTextKey);
		}

		if (badgeImageUrl == null) {
			badgeImpl.setBadgeImageUrl(StringPool.BLANK);
		}
		else {
			badgeImpl.setBadgeImageUrl(badgeImageUrl);
		}

		if (createDate == Long.MIN_VALUE) {
			badgeImpl.setCreateDate(null);
		}
		else {
			badgeImpl.setCreateDate(new Date(createDate));
		}

		badgeImpl.resetOriginalValues();

		return badgeImpl;
	}

	public long badgeId;
	public String badgeType;
	public String badgeTextKey;
	public String badgeImageUrl;
	public long createDate;
}