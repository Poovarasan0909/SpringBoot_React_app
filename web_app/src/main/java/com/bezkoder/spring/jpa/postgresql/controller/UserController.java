package com.bezkoder.spring.jpa.postgresql.controller;

import com.bezkoder.spring.jpa.postgresql.manager.UserManager;
import com.bezkoder.spring.jpa.postgresql.model.User;
import com.bezkoder.spring.jpa.postgresql.repository.UserRepository;
import com.bezkoder.spring.jpa.postgresql.service.Help;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserController extends DefaultErrorAttributes {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userManager;

    @Autowired
    private Help help;


   //create---
    @PostMapping(value = "/createUser")
    public Map<String, Object> createUser(@RequestBody Map<String,Object> body) {
         return userManager.createUser(body);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
        if(userRepository.existsById(id)){
            return "userId not exit in database";
        } else {
            return "user deleted";
        }
    }
    //update--
    @PostMapping(value = "/updateUser/{id}")
    public Map<String, Object> updateUser(@PathVariable Long id, @RequestBody Map<String,Object> body){
        Map<String,Object> result = new HashMap<>();
        String name = body.get("name").toString();
        String age = body.get("age").toString();
        String sex = body.get("sex").toString();
        System.out.println(id);
     User user = userRepository.getReferenceById(id);
     user.setName(name);
     user.setAge(age);
     user.setSex(sex);
     userRepository.save(user);
     return result;
    }


    @RequestMapping(value="/params", method = RequestMethod.GET)
    public Map getParams(@RequestParam Map<String, String> params ) {
        return params ;
    }

    @GetMapping(value = "/getAllUser")
    public List<User> getAllUser() throws IOException {
        return userRepository.getAllUser();
    }

    @RequestMapping("/error")
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerErrorResponse() {
        return "response code: 500";
    }

    @RequestMapping("/500Error1")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> internalServerError1() {
        String responseBody = "response code: 500";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
        headers.set("REASON PHRASE", "Custom Reason Phrase");

        return new ResponseEntity<>(responseBody, headers, status);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(HttpHeaders.).build();
    }

    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam("query") String query) {
        throw new RuntimeException("Internal Server Error");
    }

    @RequestMapping("/testing")
    public void test() throws JsonProcessingException {
        String str = "{\"response code\":\"500\"}";

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> dataMap = mapper.readValue(str, new TypeReference<Map<String, Object>>() {
        });

// Accessing the stored data
        String value1 = (String) dataMap.get("response code");
//        String value2 = (String) dataMap.get("key2");

        System.out.println(value1); // Output: value1
//        System.out.println(value2); // Output: value2
    }

    @PostMapping("/500Error")
    public ResponseEntity<Object> internalServerError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"responseMessage\":\"Internal Server Error\"}");
    }

    @PostMapping("/500Error2")
    public ResponseEntity<Object> internalServerError2() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"responseMessage\":\"Internal Server Error\"}");
    }

   @GetMapping("/json")
    public String json() {
       JSONObject jsonObject = new JSONObject();
       String str = "{APIUser=SANDBOX452, Options=0001, CustomerID=, type=achworks, CustomerRoutingNo=098765432, FrontEndTrace=D3_25513_MAA, CustomerAcctNo=112233, referenceNumber=N-1342-MAA-A01, AuthKey=APS6EA7D63B1566CF79C7E3A00FEA50F244D7985D75, TransactionCode=CCD, LocID=1043, CustomerName=DOE, JANINE, AcctSet=, requestType=Debit Instruction, CustTransType=D, OriginatorName=SANDBOX 4521, CheckOrTransDate=06/09/2023, APIKey=QEVBMXPGJNOUUASAFJ9XBPNQUUNE7B, SSS=SAN, CustomerAcctType=C, TransAmount=1000, TokenID=, CheckNo=, Memo=1234568, EffectiveDate=06/09/2023}";
       System.out.println(str.indexOf('{')+"==gdfjocfg--"+str.lastIndexOf('}'));
       Map<String,String> result = new HashMap<>();
       String json = "";
       if(str.indexOf('{') == 0 && str.lastIndexOf('}') == str.length()-1){
           str = str.substring(1,str.length()-1);
           String[] data = str.split(",");
           for(String d : data) {
               String[] s = d.split("=");
               if(s.length == 2) {
                   result.put(s[0].trim(),s[1].trim());
                   jsonObject.put(s[0].trim(),s[1].trim());
               } else if(s.length == 1) {
                   result.put(s[0].trim(),"");
                   jsonObject.put(s[0].trim(),"null");
               }
           }
           System.out.println(result);
//           json = objectMapper.writeValueAsString(result);
           System.out.println(jsonObject);
       }
        return jsonObject.toString();
   }
   @GetMapping("/database")
   public List<Map<String, Object>> database() throws IOException {
       ObjectMapper objectMapper = new ObjectMapper();
       JSONObject mapJson = new JSONObject();

       // Iterate through the list and map
       for (int i = 0; i < help.getAllTable().size(); i++) {
           Map<String, Object> dataMap = help.getAllTable().get(i);

           // Iterate through the map and add key-value pairs to the JSONObject
           for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
               String key = entry.getKey();
               Object value = entry.getValue();
               mapJson.put(key, value);
           }

           // Add the map JSON object to the main JSONObject

       }

       String json = objectMapper.writeValueAsString(mapJson);

       objectMapper.writeValue(new File("/home/msuser1/rrrr.json"),json);

       return help.getAllTable();
   }






}
