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
import com.liferay.portal.kernel.exception.SystemException;

import net.sareweb.barazkide.model.Rating;
import net.sareweb.barazkide.service.RatingLocalServiceUtil;
import net.sareweb.barazkide.service.base.RatingServiceBaseImpl;

/**
 * The implementation of the rating remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link net.sareweb.barazkide.service.RatingService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author A.Galdos
 * @see net.sareweb.barazkide.service.base.RatingServiceBaseImpl
 * @see net.sareweb.barazkide.service.RatingServiceUtil
 */
public class RatingServiceImpl extends RatingServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link net.sareweb.barazkide.service.RatingServiceUtil} to access the rating remote service.
	 */
	
	public void addRating(long userId, String rateType, long ratedObjectId, double rate) throws SystemException{
		Rating rating = RatingLocalServiceUtil.createRating(CounterLocalServiceUtil.increment());
		rating.setUserId(userId);
		rating.setRateType(rateType);
		rating.setRatedObjectId(ratedObjectId);
		rating.setRate(rate);
		rating.setRatingDate(new Date());
		
		RatingLocalServiceUtil.addRating(rating);
	}
	
	public double getAvgRating(long ratedObjectId){
		try {
			return ratingFinder.getAvgRating(ratedObjectId);
		} catch (SystemException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}