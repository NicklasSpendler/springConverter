package com.example.converter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "home/index";
    }

    @RequestMapping(value = "convert", method = {RequestMethod.GET})
    public String convert(@RequestParam double temp, String tempType, Model model){
        double newTemp = 0;

        if(tempType.equalsIgnoreCase("fah")){
            newTemp = (temp * 1.8) + 32;
        }else if(tempType.equalsIgnoreCase("cel")){
            newTemp = ((temp - 32)*5)/9;
        }

        model.addAttribute("newTemp", newTemp);
        model.addAttribute("tempType", "Temp Type: " + tempType);
        return "home/convert";
    }

    @RequestMapping(value = "/convertPost", method = RequestMethod.POST)
    public String convertPost(HttpServletRequest request, Model model){
        String temp = request.getParameter("temp");
        String tempType = request.getParameter("tempType");

        double newTemp = 0;
        Double parsedTemp = Double.parseDouble(temp);

        if(tempType.equalsIgnoreCase("fah")){
            newTemp = (parsedTemp * 1.8) + 32;
        }else if(tempType.equalsIgnoreCase("cel")){
            newTemp = ((parsedTemp - 32)*5)/9;
        }



        model.addAttribute("newTemp", newTemp);
        model.addAttribute("tempType", tempType);

        return "home/convert";
    }
}
