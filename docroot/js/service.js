Liferay.Service.register("Liferay.Service.Barazkide", "net.sareweb.barazkide.service", "Barazkide-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Barazkide, "Badge",
	{
		getGardenBadges: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Barazkide, "Event",
	{
		addEvent: true,
		findEventsInFollowedGardensOlderThanDate: true,
		findEventsInFollowedGardensNewerThanDate: true,
		findEventsInGardenOlderThanDate: true,
		findEventsInGardenNewerThanDate: true,
		findImageTypeEventsInGardenOlderThanDate: true,
		findImageTypeEventsInGardenNewerThanDate: true
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
		updateGardenImage: true,
		updateGardenLocation: true,
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
		addMembershipRequest: true,
		acceptMembershipRequest: true,
		rejectMembershipRequest: true,
		removeMembership: true,
		findMemberUsers: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Barazkide, "Rating",
	{
		addRating: true,
		getAvgRating: true
	}
);