package com.example.friendm;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StringUtils.collectionToDelimitedString;
//import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendmApplicationTests {

	@Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

	@Before
    public void setUp() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
            .apply(documentationConfiguration(this.restDocumentation))
            .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
            .build();
	}

	@Test
    public void connect() throws Exception {

        Map<String, Object> friends = new HashMap<>();
        friends.put("friends", Arrays.asList("andy@example.com", "john@example.com"));

        this.mockMvc.perform(post("/connect").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(friends)))
            .andExpect(status().isOk())
			.andDo(document("connect-post-example",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestFields(fieldWithPath("friends").description("The list of friends for creating a connection"))));
    }

	@Test
    public void friends() throws Exception {

        Map<String, Object> friends = new HashMap<>();
        friends.put("friends", Arrays.asList("andy@example.com", "john@example.com"));

        this.mockMvc.perform(post("/connect").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(friends)))
            .andExpect(status().isOk());

        Map<String, Object> email = new HashMap<>();
        email.put("email", "andy@example.com");

        this.mockMvc.perform(get("/friends").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(email)))
            .andExpect(status().isOk())
			.andDo(document("friends-get-example",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestFields(fieldWithPath("email").description("The user email address for retrieving the list of friends"))));
	}

	@Test
    public void common() throws Exception {

        Map<String, Object> friends = new HashMap<>();
        friends.put("friends", Arrays.asList("andy@example.com", "common@example.com"));

        this.mockMvc.perform(post("/connect").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(friends)))
            .andExpect(status().isOk());

        friends = new HashMap<>();
        friends.put("friends", Arrays.asList("john@example.com", "common@example.com"));

        this.mockMvc.perform(post("/connect").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(friends)))
			.andExpect(status().isOk());

		friends = new HashMap<>();
		friends.put("friends", Arrays.asList("andy@example.com", "john@example.com"));

        this.mockMvc.perform(get("/common").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(friends)))
            .andExpect(status().isOk())
			.andDo(document("common-get-example",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestFields(fieldWithPath("friends").description("The list of friends for retrieving the list of common friends"))));
	}

	@Test
    public void subscribe() throws Exception {

        Map<String, Object> requestor = new HashMap<>();
        requestor.put("requestor", "lisa@example.com");
        requestor.put("target", "john@example.com");

        this.mockMvc.perform(put("/subscribe").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(requestor)))
            .andExpect(status().isOk())
			.andDo(document("subscribe-put-example",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestFields(fieldWithPath("requestor").description("The user email who wants to subscribe for updates"),
							fieldWithPath("target").description("The user email whom the requestor wants updates from"))));
	}

	@Test
    public void block() throws Exception {

		Map<String, Object> friends = new HashMap<>();
        friends.put("friends", Arrays.asList("andy@example.com", "john@example.com"));

        this.mockMvc.perform(post("/connect").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(friends)))
            .andExpect(status().isOk());

        Map<String, Object> requestor = new HashMap<>();
        requestor.put("requestor", "andy@example.com");
        requestor.put("target", "john@example.com");

        this.mockMvc.perform(put("/block").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(requestor)))
            .andExpect(status().isOk())
			.andDo(document("block-put-example",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestFields(fieldWithPath("requestor").description("The user email who wants to block updates"),
							fieldWithPath("target").description("The user email whom the requestor wants to block"))));
	}

	@Test
    public void broadcast() throws Exception {

		Map<String, Object> friends = new HashMap<>();
        friends.put("friends", Arrays.asList("john@example.com", "lisa@example.com"));

        this.mockMvc.perform(post("/connect").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(friends)))
            .andExpect(status().isOk());

		Map<String, Object> sender = new HashMap<>();
        sender.put("sender", "john@example.com");
        sender.put("text", "Hello World! kate@example.com");

        this.mockMvc.perform(get("/broadcast").contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(sender)))
            .andExpect(status().isOk())
			.andDo(document("broadcast-get-example",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestFields(fieldWithPath("sender").description("The user email who wants to broadcast updates"),
							fieldWithPath("text").description("The broadcast message"))));
	}

	@Test
	public void contextLoads() {
	}

}
