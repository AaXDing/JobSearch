package com.example.jobsearch.external;

import com.example.jobsearch.entity.ExtractRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.google.common.util.concurrent.RateLimiter;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class NLPCloudClient {
    private static final String API_KEY = "Token a59429f1d281af9b79e56fad47313ac455ec6261";

    private static final String URL = "https://api.nlpcloud.io/v1/gpu/async/finetuned-llama-2-70b/kw-kp-extraction";

    public static void main (String[] args) {
        NLPCloudClient client = new NLPCloudClient();
        String s = "Official Payroll Title\\nApplication Developer II\\n...\\nREPOSTING\\nThis job posting is being reposted to accept applications on a continuous basis until the needs of service are met.\\n\\nWhen to Apply\\nSeptember 11, 2023, 8:00 AM (PT)\\n\\nThis examination will remain open until the needs of the service\\nare met and is subject to closure without prior notice.\\n\\nExam Number\\nR2521A-R\\n\\nType of Recruitment\\n\\nOpen Competitive Job Opportunity\\n\\nWho are we looking for?\\nWe are recruiting highly motivated career-minded individuals to fill the position of Software Engineer. For a full description of the position, please click HERE\\n\\nWe invite you to explore the possibility of joining a team who will turn challenges into opportunities and share the future of public sector IT!\\n\\nWith more than 10 million residents, Los Angeles County is the most populous county in the nation. As the largest employer in Southern California, over 112,000 employees in more than 38 departments provide vital public services as diverse as law enforcement, property assessment, public health protection, water conservation, cultural activities and many more.\\n\\nWe are dedicated professionals committed to preserving the dignity and integrity of the workplace as well as protecting the rights of each employee. With more than 2,300 different job descriptions, the County offers a lifetime of opportunities and careers! We offer one of the strongest public-sector benefits packages in the nation. Join us and discover a rich selection of healthcare options, robust retirement plans, and the flexibility to work, relax, and rejuvenate in order to reach your fullest personal and professional potential.\\n\\nCheck Out Our Outstanding Benefits! Click here to see a list of employee benefits.\\n• Analyzes, designs, develops, tests, debugs, installs, and deploys application software programs by applying software/application development concepts in order to create/maintain/modify new or existing business applications and interfaces according to user stories requirements and program specifications in the application development environments.\\n• Participates in gathering software requirements and develops requirement specifications, user stories, functional and technical design specifications, user manuals, and/or release notes by collaborating with stakeholders.\\n• Develops and supports front-end software applications including the areas of user interfaces, mobility, business intelligence and reporting, and enterprise content management by utilizing various development tools.\\n• Develops and supports middle-tier software applications including the areas of web services application integration, business process management, business rules, API governance, and application security by utilizing various development tools.\\n• Develops and supports back-end software applications in the areas of data integration and data replication, data modeling, data security, artificial intelligence/machine learning, and database management by utilizing various developmental tools.\\n• Develops and/or supports tools and automation processes including the areas of release management, configuration management, source control, and operational support by utilizing various developmental tools.\\n• Performs unit, functional, integration, automated system and/or performance testing for new, modified or existing complex software applications by utilizing various test tools.\\n• Provides support pertaining to application issues in various environments (e.g., development, quality assurance, training, production) by investigating, troubleshooting, and resolving application function problems.\\n• Researches application development tools and methodologies by utilizing various reference tools in order to provide recommendations to departmental management team for improving software.\\nWe are looking for dedicated individuals who meet the following requirement:\\n\\nTwo (2) years of recent experience within the last five years analyzing, designing, evaluating, developing, coding, testing, and maintaining application systems.\\n\\nIf you served in the military and were honorably discharged, you may qualify for Veteran's Credit. We will need a copy of your form DD214 to review, so please include that with your application.\\n\\nLicense\\nA valid California Class C Driver's License or the ability to utilize an alternative method of transportation when needed to carry out job-related essential functions.\\n\\nPhysical Class\\nII – Light. This class includes administrative and clerical positions requiring light physical effort that may include occasional light lifting to a 10-pound limit and some bending, stooping, or squatting. Considerable ambulation may be involved.\\n\\nOur Assessment Process\\n\\nThis examination will consist of TWO (2) parts:\\n\\nPart I: Multiple choice and/or simulation assessment(s), weighted 40%, assessing:\\n• Deductive Reasoning\\n• Professional Potential\\n• Achievement\\n• Maintaining Good Working Relationships\\n• Analyzing Information\\n• Learning Quickly\\n• Generating New Ideas\\n• Using Time Efficiently\\n• Working to High Quality Standards\\n• Adapting to Change\\n• Coping with Uncertainty\\n• Willingness to Learn\\n• Responsibility\\n\\nCandidates may be invited to participate in both Part I and II. However, only those that achieve a passing score of 70% or higher in Part I, will have Part II scored.\\n\\nPart II: Multiple choice and/or simulation assessment(s), weighted 60%, assessing:\\n• Programming Concepts\\n• HTML/CSS\\n• Automata Pro\\n\\nMULTIPLE CHOICE AND/OR SIMULATION ASSESSMENTS ARE NOT REVIEWABLE BY CANDIDATES PER CIVIL SERVICE RULE 7.19.\\n\\nAPPLICANTS MUST MEET THE REQUIREMENTS AND ACHIEVE A PASSING SCORE OF 70% OR HIGHER ON EACH PART OF THE EXAMINATION IN ORDER TO BE PLACED ON THE ELIGIBLE LIST.\\n\\nPlease Note\\nTest Invitation Letters and other correspondence will be sent electronically to the e-mail address provided on the application. It is important that applicants provide a valid e-mail address. Please add jcheung@hr.lacounty.gov, info@governmentjobs.com, talentcentral@shl.com, and donot-reply@amcatmail.com to your e-mail address book and/or list of approved senders to prevent e-mail notifications from being filtered as SPAM/JUNK mail.\\n\\nNotice of Non-Acceptance and Final Result letters will be sent electronically to your e-mail address. Test scores cannot be given over the telephone.\\n\\nTest Preparation\\nStudy guides and other test preparation resources are available to help candidates prepare for employment tests. While the guides will help you prepare for the test, we advise you to review all related materials that you deem necessary.\\n• An interactive, Online Test Preparation System for taking practice tests may be accessed on the Department of Human Resources website at http://hr.lacounty.gov/. Please click on \\\"Find A Job\\\" and then \\\"Job Search Toolkit.\\\" Test preparation information is located under the \\\"Employment Test Assistance\\\" section.\\n• Additional online practice tests are available at https://www.shldirect.com/en-us/practice-tests\\nPlease note that these resources are intended to provide general information about the types of tests and assessments used by the County of Los Angeles. These resources do not contain details of the exam nor the specific questions you will be asked to answer during the test administration. The exam content section describes the areas assessed by the test and you are invited to study and review whatever material you believe will help you to prepare.\\n\\nTransfer of Test Components\\n• Applicants who have taken identical test components recently for other exams may have their responses automatically transferred to this examination.\\n• This examination contains test components that may be used in the future for new examinations and your test responses may be transferred.\\n\\nEligibility Information\\nThe names of candidates receiving a passing grade in the examination will be placed on the eligible register in the order of their score group for a period of 12 months from the date of the promulgation.\\n\\nApplications will be processed on an as-received basis and promulgated to the register of eligible candidates accordingly.\\n\\nVacancy Information\\nThe resulting eligible register will be used to fill vacancies throughout various Los Angeles County departments as they occur.\\n\\nHow to Apply\\nApplications must be submitted online only. We must receive your application BEFORE 5:00 p.m. (PT) on the last day of filing. Applications submitted by U.S. Mail, Fax, or in person will not be accepted.\\n\\nPlan to submit your online application well in advance of the 5:00 p.m. (PT) deadline on the last day of filing as you may be required to verify your email address. This only needs to be done once per email address, and if you already have a job seeker account on governmentjobs.com/careers/lacounty, you can verify at any time by logging in and following the prompts. This is to enhance the security of your online application and to ensure you do not enter an incorrect email address.\\n\\nApply online by clicking the green \\\"Apply\\\" button at the top right of this posting. You can also track the status of your application using this website. Fill out your application completely. The acceptance of your application depends on whether you have clearly shown that you meet the requirements to qualify. Provide any relevant job experience in the spaces provided so we can evaluate your qualifications for the job. For each job held, give the name and address of your employer, your job title, beginning and ending dates, number of hours worked per week, and description of work performed. If your application and/or supplemental questionnaire is incomplete, it will be rejected.\\n\\nImportant: Please note that all information included in the application materials is subject to verification at any point during the examination and hiring process, including after an appointment has been made. Falsification of any information may result in disqualification or rescission of appointment. Utilizing verbiage from the Class Specification and Requirements serving as your description of duties will not be sufficient to demonstrate that you meet the Requirements. Doing so may result in an incomplete application and you may be disqualified.\\n\\n___________________________________________________________________________\\n\\nAnti-Racism, Diversity, and Inclusion (ARDI)\\nThe County of Los Angeles recognizes and affirms that all people are created equal and are entitled to all rights afforded by the Constitution of the United States. The Department of Human Resources is committed to promoting Anti-racism, Diversity, and Inclusion efforts to address the inequalities and disparities amongst race. We support the ARDI Strategic Plan and its goals by improving equality, diversity, and inclusion in recruitment, selection, and employment practices.\\n\\nComputer and Internet Access at Public Libraries\\nFor candidates who may not have regular access to a computer or the internet, applications can be completed on computers at public libraries throughout Los Angeles County.\\n\\nNo Sharing of User ID and Password\\nAll applicants must file their application online using their own user ID and password. Using a family member's or friend's user ID and password may erase a candidate's original application record.\\n\\nTesting Accommodation\\nIf you require an accommodation to fairly compete in any part of the assessment process, you will be given the opportunity to make a request when completing your application. Please note, you may be required to submit documentation from a qualified medical provider or other qualified professional to support your request for a testing accommodation.\\n\\nCalifornia Relay Services Phone: (800) 735-2922\\nTesting Accommodations Coordinator: TestingAccommodations@hr.lacounty.gov\\nTeletype Phone: (800) 899-4099\\nAlternate Teletype Phone: (800) 897-0077\\n\\nDepartment Contact Name: James Cheung\\nDepartment Contact Phone: (213) 738-2361\\nDepartment Contact Email: jcheung@hr.lacounty.gov";
        client.extract(s);
    }

    public Set<String> extract(String article) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        //Initiate post request and set headers
        HttpPost request = new HttpPost(URL);
        request.setHeader("Content-type", "application/json");
        request.setHeader("Authorization", API_KEY);
        ExtractRequestBody body = new ExtractRequestBody(article);

        System.out.println("header created");

        String jsonBody;

        //Set article to json
        try {
            jsonBody = mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

        //Set json to requestBody
        try {
            request.setEntity(new StringEntity(jsonBody));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

        System.out.println("post reqeust body created");

        //Setup post response handler
        ResponseHandler<String> postResponseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 202) { //If not 200OK return empty set
                System.out.println("status code" + response.getStatusLine().getStatusCode());
                return "";
            }
            HttpEntity entity = response.getEntity(); //initiate entity, if entity failed return empty set
            if (entity == null) {
                System.out.println("entity empty");
                return "";
            }

            System.out.println("post response get succeed");

            JsonNode rootNode = mapper.readTree(entity.getContent());
            System.out.println("post request read tree succeed");
            return rootNode.get("url").asText();
        };

        String url = "";

        //Run HTTP post request, return a url string
        try {
            url = httpClient.execute(request, postResponseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(url);

//        String url = "https://api.nlpcloud.io/v1/get-async-result/847cf3b4-e42b-4bbc-bf40-9f45b58219ee";

        //Set up a get response handler
        ResponseHandler<Set<String>> responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) { //If not 200OK return empty set
                return Collections.emptySet();
            }
            HttpEntity entity = response.getEntity(); //initiate entity, if entity failed return empty set
            if (entity == null) {
                return Collections.emptySet();
            }

            JsonNode root = mapper.readTree(entity.getContent());
            JsonNode contentNode = root.get("content");
            //TENTATIVE
            JsonNode contentRootNode = mapper.readTree(contentNode.asText());
            JsonNode keywordsNode = contentRootNode.get("keywords_and_keyphrases");
            //END TENTATIVE

            Set<String> keywords = new HashSet<>();
            Iterator<JsonNode> item_it = keywordsNode.elements();
            while (item_it.hasNext()) {
                JsonNode itemNode = item_it.next();
                String keyword = itemNode.asText();
                keywords.add(keyword);
                System.out.println(keyword);
            }

            return keywords;
        };

        //Initiate a get request
        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeader("Authorization", API_KEY);

        //Execute get request
        RateLimiter rateLimiter = RateLimiter.create(1);
        int counter = 0;
        while (counter < 50) {
            rateLimiter.acquire(1);
            try {
                Set<String> keywords = httpClient.execute(getRequest, responseHandler);
                if (!keywords.isEmpty())
                    return keywords;
            } catch (IOException e) {
    //            e.printStackTrace();
            }
            counter += 1;
        }

        return Collections.emptySet();
    }

}
