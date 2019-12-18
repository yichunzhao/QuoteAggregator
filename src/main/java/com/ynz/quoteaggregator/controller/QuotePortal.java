package com.ynz.quoteaggregator.controller;

import com.ynz.quoteaggregator.domainmodel.Role;
import com.ynz.quoteaggregator.dto.MyQuoteDto;
import com.ynz.quoteaggregator.entities.MyQuote;
import com.ynz.quoteaggregator.entities.User;
import com.ynz.quoteaggregator.entities.UserRole;
import com.ynz.quoteaggregator.exceptions.PermissionException;
import com.ynz.quoteaggregator.qodcontext.QuoteResponse;
import com.ynz.quoteaggregator.service.MyQuoteService;
import com.ynz.quoteaggregator.simpsonscontext.QuoteContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api")
public class QuotePortal {

    public static final String QodQuoteUrl = "http://quotes.rest/qod.json";

    private static final Map<String, String> quoteApis = new HashMap<>();

    static {
        quoteApis.put("GlitchQuoteResourceUrl", "https://thesimpsonsquoteapi.glitch.me/quotes");
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MyQuoteService myQuoteService;


    @GetMapping("/quotes/qod")
    public QuoteResponse getOneQuoteAtRandom() {
        QuoteResponse response = restTemplate.getForObject(QodQuoteUrl, QuoteResponse.class);
        return response;
    }

    @GetMapping("/{count}/quotes/glitch")
    public QuoteContainer[] GetManyQuotes(@PathVariable("count") int count) {
        String url = quoteApis.get("GlitchQuoteResourceUrl") + "?count=" + count;
        QuoteContainer[] result = restTemplate.getForObject(url, QuoteContainer[].class);
        return result;
    }

    @GetMapping("/quotes/glitch")
    public QuoteContainer[] GetSingleQuote() {
        QuoteContainer[] result = restTemplate.getForObject(quoteApis.get("GlitchQuoteResourceUrl"), QuoteContainer[].class);
        return result;
    }

    //store a quote for a user in his quote frame.
    @PostMapping("/{loginName}/my-quotes")
    public MyQuoteDto storeUserQuote(@PathVariable(value = "loginName") String loginName, @Valid @RequestBody MyQuoteDto myQuoteDto) {
        User loginUser = myQuoteService.findUserByLoginName(loginName);

        if (loginUser == null) {
            loginUser = new User();
            loginUser.setLoginName(loginName);

        }


        MyQuote myQuote = new MyQuote(myQuoteDto);
        myQuote.setUser(loginUser);

        //persist.
        MyQuote saved = myQuoteService.saveUserMyQuote(myQuote);
        MyQuoteDto savedMyQuoteDto = new MyQuoteDto(saved);
        return savedMyQuoteDto;
    }

    //get hall of frame...
    @GetMapping("/{loginName}/my-quotes")
    public List<MyQuoteDto> getUserStoredQuotes(@PathVariable(value = "loginName") String loginName) {

        List<MyQuote> found = myQuoteService.findUserMyQuoteByLoginName(loginName);

        return found.stream().map(mq -> new MyQuoteDto(mq)).collect(toList());
    }

    //post a new quote api
    @PostMapping("/{loginName}/aggregator/{quoteApi}/quote-api/{apiName}/quote-api-key")
    public void addQuoteApi(@PathVariable(value = "loginName") String loginName,
                            @PathVariable(value = "quoteApi") String quoteApi,
                            @PathVariable(value = "apiName") String apiName) {

        Set<UserRole> roles = myQuoteService.findUserRolesByLoginName(loginName);

        if (roles.isEmpty() || (!roles.stream().map(x -> x.getRole()).collect(toSet()).contains(Role.ADMIN))) {
            throw new PermissionException("Having no permission to access.");
        }

        //api-name -> key,
        if (quoteApis.containsKey(apiName)) throw new IllegalArgumentException("apiName is already existed.");
        if (quoteApis.containsValue(quoteApi)) throw new IllegalArgumentException("quoteApi is already existed.");

        quoteApis.put(apiName, quoteApi);
    }

    //post a new user with a role.
    @PostMapping("/{loginName}/aggregator/{role}/Users")
    public UserRole createUserWithRole(@PathVariable(value = "loginName") String loginName,
                                       @PathVariable(value = "role") Role role) {
        //
        User user = myQuoteService.findUserByLoginName(loginName);

        if (user == null) {
            //create a new user and its role.
            user = new User();
            user.setLoginName(loginName);
        }

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        return myQuoteService.saveUserRole(userRole);


    }

}
