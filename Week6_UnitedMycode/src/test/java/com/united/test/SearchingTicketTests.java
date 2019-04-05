package com.united.test;

import org.testng.annotations.Test;

import com.library.Base;
import com.united.pages.HomePage;

public class SearchingTicketTests extends Base {
	HomePage myHomepage = new HomePage();

	@Test
	public void searchvacationTicket() {
		myHomepage.goto_United_Website();
		myHomepage.selectOneWay();
		myHomepage.selectRoundTrip();
		myHomepage.enterFromAirport("Washington", 3);
		myHomepage.enterToAirport("New York", 3);

	}

}
