Liferay.Service.register("Liferay.Service.Barazkide", "net.sareweb.barazkide.service", "Barazkide-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Barazkide, "Event",
	{
		findEventsInFollowedGardensOlderThanDate: true,
		findEventsInFollowedGardensNewerThanDate: true,
		findEventsInGardenOlderThanDate: true,
		findEventsInGardenNewerThanDate: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Barazkide, "Following",
	{
		addFollowing: true,
		removeFollowing: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Barazkide, "Garden",
	{
		addGarden: true,
		getGardens: true,
		getUserGardensFromDate: true,
		getFollowedGardensOlderThanDate: true,
		getFollowedGardensNewerThanDate: true,
		getParticipatingGardensOlderThanDate: true,
		getParticipatingGardensNewerThanDate: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Barazkide, "Membership",
	{
		addMembership: true,
		removeMembership: true,
		findMemberUsers: true
	}
);