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

import net.sareweb.barazkide.model.Rating;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Rating in entity cache.
 *
 * @author A.Galdos
 * @see Rating
 * @generated
 */
public class RatingCacheModel implements CacheModel<Rating>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{ratingId=");
		sb.append(ratingId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", rateType=");
		sb.append(rateType);
		sb.append(", ratedObjectId=");
		sb.append(ratedObjectId);
		sb.append(", ratingDate=");
		sb.append(ratingDate);
		sb.append(", rate=");
		sb.append(rate);
		sb.append("}");

		return sb.toString();
	}

	public Rating toEntityModel() {
		RatingImpl ratingImpl = new RatingImpl();

		ratingImpl.setRatingId(ratingId);
		ratingImpl.setUserId(userId);

		if (rateType == null) {
			ratingImpl.setRateType(StringPool.BLANK);
		}
		else {
			ratingImpl.setRateType(rateType);
		}

		ratingImpl.setRatedObjectId(ratedObjectId);

		if (ratingDate == Long.MIN_VALUE) {
			ratingImpl.setRatingDate(null);
		}
		else {
			ratingImpl.setRatingDate(new Date(ratingDate));
		}

		ratingImpl.setRate(rate);

		ratingImpl.resetOriginalValues();

		return ratingImpl;
	}

	public long ratingId;
	public long userId;
	public String rateType;
	public long ratedObjectId;
	public long ratingDate;
	public double rate;
}