package org.example.exercisevalidation.Controller;

import jakarta.validation.Valid;
import org.example.exercisevalidation.Model.Project;
import org.example.exercisevalidation.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity<?> getProjects(){
        return ResponseEntity.status(200).body(projects);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestBody @Valid Project project, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@PathVariable String id , @RequestBody @Valid Project project, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        for (int i=0;i< projects.size();i++){
            if (projects.get(i).getID().equals(id)){
                project.setID(id);
                projects.set(i , project);
                return ResponseEntity.status(200).body(new ApiResponse("Project Updated Successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project Not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletProject(@Valid @PathVariable String id){
        for (int i=0;i< projects.size();i++){
            if (projects.get(i).getID().equals(id)){
                projects.remove(i);
                return ResponseEntity.status(200).body(new ApiResponse("Project Deleted Successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project Not found"));
    }


    @PutMapping("/status/{id}")
    public ResponseEntity<?> statusProject(@Valid @PathVariable String id){
        for (int i=0;i< projects.size();i++){
            if (projects.get(i).getID().equals(id)){
                if (projects.get(i).getStatus().equalsIgnoreCase("not started")){
                    projects.get(i).setStatus("in progress");
                    return ResponseEntity.status(200).body(new ApiResponse("Project Status Changed Into in progress"));
                }else if (projects.get(i).getStatus().equalsIgnoreCase("in progress")){
                    projects.get(i).setStatus("completed");
                    return ResponseEntity.status(200).body(new ApiResponse("Project Status Changed Into completed"));
                }else {
                    projects.get(i).setStatus("not started");
                    return ResponseEntity.status(200).body(new ApiResponse("Project Status Changed Into not started"));
                }
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("Project Not found"));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> titleProject(@Valid @PathVariable String title){
        for (int i=0;i< projects.size();i++) {
            if (projects.get(i).getTitle().equalsIgnoreCase(title)) {
                return ResponseEntity.status(200).body(projects.get(i));
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("Project not found"));
    }

    @GetMapping("/company-name/{company}")
    public ResponseEntity<?> companyNameProjects( @Valid @PathVariable String company){
        ArrayList<Project> result = new ArrayList<>();

        for (int i=0;i< projects.size();i++){
            if (projects.get(i).getCompanyName().equalsIgnoreCase(company)){
                result.add(projects.get(i));
            }
        }
        if (result.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No Projects found"));
        }
        return ResponseEntity.status(200).body(result);
    }
}
