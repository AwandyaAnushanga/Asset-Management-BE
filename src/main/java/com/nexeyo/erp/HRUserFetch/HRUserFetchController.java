package com.nexeyo.erp.HRUserFetch;

import com.nexeyo.erp.jwt.models.User;
import com.nexeyo.erp.jwt.repository.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/hr-user-fetch")
public class HRUserFetchController {
    @Autowired
    HRUserFetchService hrUserFetchService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "")
    ResponseEntity<?> Fetch() throws JSONException {
        return hrUserFetchService.Fetch();
    }

    @PostMapping(path = "")
    ResponseEntity<?> Add(@RequestBody HRUserFetch hrUserFetch) {
        return hrUserFetchService.Add(hrUserFetch);
    }

    @PutMapping(path = "")
    ResponseEntity<?> Update(@RequestBody HRUserFetch hrUserFetch) {
        return hrUserFetchService.Update(hrUserFetch);
    }

    @GetMapping(path = "view/{page}/{page_size}")
    ResponseEntity<?> View(@PathVariable int page, @PathVariable int page_size) throws JSONException {
        return hrUserFetchService.GetAll(page, page_size);
    }

    @PostMapping(path = "search/{page}/{page_size}")
    ResponseEntity<?> Search(@PathVariable int page, @PathVariable int page_size, @RequestParam String name) throws JSONException {
        return hrUserFetchService.Search(page, page_size, name);
    }

    @PostMapping(path = "search-erp/{page}/{page_size}")
    ResponseEntity<?> SearchByWithoutPicked(@PathVariable int page, @PathVariable int page_size, @RequestParam String name) throws JSONException {
        return hrUserFetchService.SearchWithoutPicked(page, page_size, name);
    }

    @PostMapping(path = "measurement-user-search/{page}/{page_size}")
    ResponseEntity<?> SearchMeasurementUsers(@PathVariable int page, @PathVariable int page_size, @RequestParam String name) throws JSONException {
        return hrUserFetchService.SearchMeasurementUsers(page, page_size, name);
    }

    @PostMapping(path = "branch-wise/{page_size}/{page}")
    ResponseEntity<?> GetBranchWise(@RequestParam int branch_id) {
        List<User> userList = userRepository.findByUsersLocationList_Location_id(branch_id);
        List<User> newUserList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getHr_user_id() != null) {
                newUserList.add(userList.get(i));
            }
        }
        return ResponseEntity.ok(newUserList);
    }
    @PostMapping(path = "emp-search")
    ResponseEntity Search(@RequestParam String emp_id) {
        return hrUserFetchService.Search(emp_id);
    }

}
