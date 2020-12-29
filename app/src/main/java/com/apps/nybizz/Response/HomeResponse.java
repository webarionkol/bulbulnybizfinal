package com.apps.nybizz.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResponse {

    public class Banner {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("place")
        @Expose
        private String place;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }



    public class Category {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }


    public class Data {

        @SerializedName("categories")
        @Expose
        private List<Category> categories = null;
        @SerializedName("banners")
        @Expose
        private List<Banner> banners = null;
        @SerializedName("vendor")
        @Expose
        private Vendor vendor;

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public List<Banner> getBanners() {
            return banners;
        }

        public void setBanners(List<Banner> banners) {
            this.banners = banners;
        }

        public Vendor getVendor() {
            return vendor;
        }

        public void setVendor(Vendor vendor) {
            this.vendor = vendor;
        }

    }


        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private Data data;
        @SerializedName("status")
        @Expose
        private Integer status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }



    public class Vendor {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("vendor_name")
        @Expose
        private String vendorName;
        @SerializedName("business_name")
        @Expose
        private String businessName;
        @SerializedName("about_me")
        @Expose
        private Object aboutMe;
        @SerializedName("total_views")
        @Expose
        private Object totalViews;
        @SerializedName("total_likes")
        @Expose
        private Object totalLikes;
        @SerializedName("profile_picture")
        @Expose
        private Object profilePicture;
        @SerializedName("products")
        @Expose
        private List<Object> products = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getVendorName() {
            return vendorName;
        }

        public void setVendorName(String vendorName) {
            this.vendorName = vendorName;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public Object getAboutMe() {
            return aboutMe;
        }

        public void setAboutMe(Object aboutMe) {
            this.aboutMe = aboutMe;
        }

        public Object getTotalViews() {
            return totalViews;
        }

        public void setTotalViews(Object totalViews) {
            this.totalViews = totalViews;
        }

        public Object getTotalLikes() {
            return totalLikes;
        }

        public void setTotalLikes(Object totalLikes) {
            this.totalLikes = totalLikes;
        }

        public Object getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(Object profilePicture) {
            this.profilePicture = profilePicture;
        }

        public List<Object> getProducts() {
            return products;
        }

        public void setProducts(List<Object> products) {
            this.products = products;
        }

    }

}
