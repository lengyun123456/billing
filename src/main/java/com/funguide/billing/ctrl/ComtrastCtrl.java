package com.funguide.billing.ctrl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.funguide.billing.dao.ContrastBillingDao;
import com.funguide.billing.entity.ContrastBilling;

@Controller
@Scope("request")
public class ComtrastCtrl{

	@RequestMapping("/")
	public String marketMain(HttpServletRequest request, ModelMap map) throws Exception {
		List<ContrastBilling> ebs = ContrastBillingDao.QueryAll("CONTRAST_BILLING");
		map.put("ebs", ebs);
		return "index";
	}
}
