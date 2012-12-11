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

package net.sareweb.barazkide.service.impl;

import java.util.Date;

import net.sareweb.barazkide.model.Membership;
import net.sareweb.barazkide.service.MembershipLocalServiceUtil;
import net.sareweb.barazkide.service.base.MembershipServiceBaseImpl;

import com.liferay.counter.service.CounterLocalServiceUtil;

/**
 * The implementation of the membership remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link net.sareweb.barazkide.service.MembershipService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author A.Galdos
 * @see net.sareweb.barazkide.service.base.MembershipServiceBaseImpl
 * @see net.sareweb.barazkide.service.MembershipServiceUtil
 */
public class MembershipServiceImpl extends MembershipServiceBaseImpl {
	
	public boolean addMembership(long userId, long gardenId){
		try{
			Membership membership = MembershipLocalServiceUtil.createMembership(CounterLocalServiceUtil.increment());
			membership.setUserId(userId);
			membership.setGardenId(gardenId);
			membership.setMembershipDate(new Date());
			MembershipLocalServiceUtil.addMembership(membership);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeMembership(long userId, long gardenId){
		try{
			Membership membership = membershipPersistence.fetchByUserAndGarden(userId, gardenId);
			MembershipLocalServiceUtil.deleteMembership(membership);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
}