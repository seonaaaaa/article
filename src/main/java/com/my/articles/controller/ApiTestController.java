package com.my.articles.controller;

import com.my.articles.dto.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApiTestController {
    @GetMapping("api")
    public String api_form() {
        return "/test/api";
    }

    @PostMapping("apiPostTest")
    public ResponseEntity<String> apiPostTest(@RequestBody LoginDTO loginDTO) {
        String info = loginDTO.getUserid() + loginDTO.getPassword();
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

    @RequestMapping(value = "getResponse", method = RequestMethod.POST)
    @ResponseBody
    public LoginDTO getResponse() {
        return new LoginDTO("zzzmini", "1234");
    }

    @PostMapping("apiPostArrayTest")
    @ResponseBody
    public Map<String, LoginDTO> apiPostArrayTest(@RequestBody List<LoginDTO> loginDTOs){
        Map<String, LoginDTO> map = new HashMap<>();
        for(LoginDTO dto : loginDTOs){
            map.put(dto.getUserid(), dto);
        }
        System.out.println(map);
        return  map;
    }

    @DeleteMapping("delete")
    @ResponseBody
    public String delete() {
        return "Delete Mapping";
    }
}
