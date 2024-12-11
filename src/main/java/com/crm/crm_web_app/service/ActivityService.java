package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Activity;
import com.crm.crm_web_app.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    // Create a new activity
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    // Retrieve all activities
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    // Retrieve activities by customer ID
    public List<Activity> getActivitiesByCustomerId(Long customerId) {
        return activityRepository.findByCustomerId(customerId);
    }

    // Retrieve activity by ID
    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    // Update an existing activity
    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id).map(activity -> {
            activity.setType(updatedActivity.getType());
            activity.setDescription(updatedActivity.getDescription());
            activity.setDateTime(updatedActivity.getDateTime());
            activity.setCustomer(updatedActivity.getCustomer());
            return activityRepository.save(activity);
        }).orElseThrow(() -> new RuntimeException("Activity not found with id " + id));
    }

    // Delete an activity
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
