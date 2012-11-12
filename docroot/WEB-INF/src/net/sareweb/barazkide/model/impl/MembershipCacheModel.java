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

import net.sareweb.barazkide.model.Membership;

import java.io.Serializable;

/**
 * The cache model class for representing Membership in entity cache.
 *
 * @author A.Galdos
 * @see Membership
 * @generated
 */
public class MembershipCacheModel implements CacheModel<Membership>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{membershipId=");
		sb.append(membershipId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", gardenId=");
		sb.append(gardenId);
		sb.append("}");

		return sb.toString();
	}

	public Membership toEntityModel() {
		MembershipImpl membershipImpl = new MembershipImpl();

		membershipImpl.setMembershipId(membershipId);
		membershipImpl.setUserId(userId);
		membershipImpl.setGardenId(gardenId);

		membershipImpl.resetOriginalValues();

		return membershipImpl;
	}

	public long membershipId;
	public long userId;
	public long gardenId;
}