package com.example.projecttrackersystem.Controller;

import com.example.projecttrackersystem.ApiResponse.ApiResponse;
import com.example.projecttrackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();


    public ProjectController() {
        projects.add(new Project("CD15312","Website","Creating website for company","Not Done","Elm"));
        projects.add(new Project("CD24322","Website","Creating website for company","Not Done","Tuwaiq Academy"));
        projects.add(new Project("CD34545","Mobile App","Creating Mobile App for company","Not Done","Elm"));
        projects.add(new Project("CD45656","ERP system","Creating ERP system for company","Not Done","Elm"));
        projects.add(new Project("CD45656","ERP system","update future ERP system for company","Not Done","STC"));
    }

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("Project added successfully");
    }

    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }
    @GetMapping("/getby-title/{title}")
    public Project getProjectsByTitle(@PathVariable String title) {
        Project project = null;
        for (Project p : projects) {
            if (p.getTitle().toLowerCase().contains(title.toLowerCase())) {
                project = p;
                break;
            }
        }
        return project;
    }
    @GetMapping("/getby-companyname/{companyName}")
    public ArrayList<Project> getProjectsByCompanyName(@PathVariable String companyName) {
        ArrayList<Project> projectSearched = new ArrayList<>();
        for (Project p : projects) {
            if (p.getCompanyName().toLowerCase().equals(companyName.toLowerCase())) {
                projectSearched.add(p);
            }
        }
        return projectSearched;
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @RequestBody Project project) {

        if (index >= projects.size() || index < 0) return new ApiResponse("Project not found");

        projects.set(index,project);
        return new ApiResponse("Project updated successfully");
    }

    @PutMapping("/update-status/{index}")
    public ApiResponse updateProjectStatus(@PathVariable int index) {

        if (index >= projects.size() || index < 0) return new ApiResponse("Project not found");

        Project project = projects.get(index);
        if(project.getStatus().equals("Not Done")){

            project.setStatus("Done");
            return new ApiResponse("Project updated status successfully");
        }else return new ApiResponse("Aleady updated to done");

    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        if (index >= projects.size() || index < 0) return new ApiResponse("Project not found");

        projects.remove(index);
        return new ApiResponse("Project deleted successfully");
    }


}
