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

package net.sareweb.barazkide.service.base;

import net.sareweb.barazkide.service.GardenServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class GardenServiceClpInvoker {
	public GardenServiceClpInvoker() {
		_methodName50 = "getBeanIdentifier";

		_methodParameterTypes50 = new String[] {  };

		_methodName51 = "setBeanIdentifier";

		_methodParameterTypes51 = new String[] { "java.lang.String" };

		_methodName56 = "addGarden";

		_methodParameterTypes56 = new String[] {
				"java.lang.String", "java.lang.String", "double", "double",
				"long"
			};

		_methodName57 = "getGardens";

		_methodParameterTypes57 = new String[] {  };

		_methodName58 = "getUserGardensFromDate";

		_methodParameterTypes58 = new String[] { "long", "long", "boolean", "int" };

		_methodName59 = "getFollowedGardensOlderThanDate";

		_methodParameterTypes59 = new String[] { "long", "long", "int" };

		_methodName60 = "getFollowedGardensNewerThanDate";

		_methodParameterTypes60 = new String[] { "long", "long", "int" };

		_methodName61 = "getParticipatingGardensOlderThanDate";

		_methodParameterTypes61 = new String[] { "long", "long", "int" };

		_methodName62 = "getParticipatingGardensNewerThanDate";

		_methodParameterTypes62 = new String[] { "long", "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return GardenServiceUtil.getBeanIdentifier();
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			GardenServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return GardenServiceUtil.addGarden((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Double)arguments[2]).doubleValue(),
				((Double)arguments[3]).doubleValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return GardenServiceUtil.getGardens();
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return GardenServiceUtil.getUserGardensFromDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return GardenServiceUtil.getFollowedGardensOlderThanDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return GardenServiceUtil.getFollowedGardensNewerThanDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return GardenServiceUtil.getParticipatingGardensOlderThanDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return GardenServiceUtil.getParticipatingGardensNewerThanDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
}