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

import com.liferay.counter.service.CounterLocalServiceUtil;

import net.sareweb.barazkide.model.Following;
import net.sareweb.barazkide.service.FollowingLocalServiceUtil;
import net.sareweb.barazkide.service.base.FollowingServiceBaseImpl;

/**
 * The implementation of the following remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link net.sareweb.barazkide.service.FollowingService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author A.Galdos
 * @see net.sareweb.barazkide.service.base.FollowingServiceBaseImpl
 * @see net.sareweb.barazkide.service.FollowingServiceUtil
 */
public class FollowingServiceImpl extends FollowingServiceBaseImpl {
	
	public Following addFollowing(long userId, long gardenId){
		try{
			Following following = FollowingLocalServiceUtil.createFollowing(CounterLocalServiceUtil.increment());
			following.setUserId(userId);
			following.setGardenId(gardenId);
			following.setFollowingDate(new Date());
			return FollowingLocalServiceUtil.addFollowing(following);
		}
		catch(Exception e){
			return null;
		}
	}
	
	public boolean removeFollowing(long userId, long gardenId){
		try{
			Following following = followingPersistence.fetchByUserAndGarden(userId, gardenId);
			FollowingLocalServiceUtil.deleteFollowing(following);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
}