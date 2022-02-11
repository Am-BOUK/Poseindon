package com.nnk.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IBidListService;
import com.nnk.springboot.services.ICurvePointService;
import com.nnk.springboot.services.IRatingService;
import com.nnk.springboot.services.IRuleNameService;
import com.nnk.springboot.services.ITradeService;
import com.nnk.springboot.services.IUserService;

@SpringBootApplication
public class SpringBootSkeletonApplication implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private IUserService userService;

	@Autowired
	private BidListRepository bidListRepository;
	@Autowired
	private IBidListService bidListService;

	@Autowired
	private CurvePointRepository curvePointRepository;
	@Autowired
	private ICurvePointService curvePointService;

	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private IRatingService ratingService;

	@Autowired
	private RuleNameRepository ruleNameRepository;
	@Autowired
	private IRuleNameService ruleNameService;

	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private ITradeService tradeService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSkeletonApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa",
//				"ADMIN");
//		User user2 = new User("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "USER");
//		userRepository.save(user);

		BidList bidList = new BidList("Account Name", "Account Type", 10.0);
//		bidListRepository.save(bidList);
//		bidListService.addNewBid(bidList);

		CurvePoint curvePoint = new CurvePoint(2, 14.00, 10.0);
//		curvePointRepository.save(curvePoint);
//		curvePointService.addCurvePoint(curvePoint);

		Rating rating = new Rating("Moody Rating", "Sand P Rating", "Fitch Rating", 1);
//		ratingRepository.save(rating);
//		ratingService.addNewRating(rating);

		RuleName ruleName = new RuleName("Rule Name", "Rule Description", "Rule json", "Rule templete", "Rule sqlStr",
				"Rule sqlPart");
//		ruleNameRepository.save(ruleName);
//		ruleNameService.addNewRuleName(ruleName);

		Trade trade = new Trade("Trade Account", "Trade Type",3.0);
//		tradeRepository.save(trade);
//		tradeService.addNewTrade(trade);

	}

}
