package com.apps.nybizz.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VendorDetailsResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("url")
    @Expose
    private String url;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public class Data {

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
        private String aboutMe;
        @SerializedName("total_views")
        @Expose
        private String totalViews;
        @SerializedName("total_likes")
        @Expose
        private String totalLikes;
        @SerializedName("profile_picture")
        @Expose
        private Object profilePicture;
        @SerializedName("products")
        @Expose
        private List<Product> products = null;

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

        public String getAboutMe() {
            return aboutMe;
        }

        public void setAboutMe(String aboutMe) {
            this.aboutMe = aboutMe;
        }

        public String getTotalViews() {
            return totalViews;
        }

        public void setTotalViews(String totalViews) {
            this.totalViews = totalViews;
        }

        public Object getTotalLikes() {
            return totalLikes;
        }

        public void setTotalLikes(String totalLikes) {
            this.totalLikes = totalLikes;
        }

        public Object getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(Object profilePicture) {
            this.profilePicture = profilePicture;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

    }


    public class Product {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("category_id")
        @Expose
        private Integer categoryId;
        @SerializedName("subcategory_id")
        @Expose
        private Integer subcategoryId;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("offer_price")
        @Expose
        private String offerPrice;
        @SerializedName("vendor")
        @Expose
        private Integer vendor;
        @SerializedName("media_type")
        @Expose
        private String mediaType;
        @SerializedName("media_url")
        @Expose
        private String mediaUrl;
        @SerializedName("productname")
        @Expose
        private String productname;
        @SerializedName("productcode")
        @Expose
        private String productcode;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("likes")
        @Expose
        private Object likes;
        @SerializedName("views")
        @Expose
        private Object views;
        @SerializedName("sku_number")
        @Expose
        private Object skuNumber;
        @SerializedName("variation_details")
        @Expose
        private Object variationDetails;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("countryOfOrigin")
        @Expose
        private Object countryOfOrigin;
        @SerializedName("warranty")
        @Expose
        private Object warranty;
        @SerializedName("legalDisclaimer")
        @Expose
        private Object legalDisclaimer;
        @SerializedName("created_by")
        @Expose
        private Integer createdBy;
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

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public Integer getSubcategoryId() {
            return subcategoryId;
        }

        public void setSubcategoryId(Integer subcategoryId) {
            this.subcategoryId = subcategoryId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
        }

        public Integer getVendor() {
            return vendor;
        }

        public void setVendor(Integer vendor) {
            this.vendor = vendor;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public String getMediaUrl() {
            return mediaUrl;
        }

        public void setMediaUrl(String mediaUrl) {
            this.mediaUrl = mediaUrl;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public String getProductcode() {
            return productcode;
        }

        public void setProductcode(String productcode) {
            this.productcode = productcode;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getLikes() {
            return likes;
        }

        public void setLikes(Object likes) {
            this.likes = likes;
        }

        public Object getViews() {
            return views;
        }

        public void setViews(Object views) {
            this.views = views;
        }

        public Object getSkuNumber() {
            return skuNumber;
        }

        public void setSkuNumber(Object skuNumber) {
            this.skuNumber = skuNumber;
        }

        public Object getVariationDetails() {
            return variationDetails;
        }

        public void setVariationDetails(Object variationDetails) {
            this.variationDetails = variationDetails;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Object getCountryOfOrigin() {
            return countryOfOrigin;
        }

        public void setCountryOfOrigin(Object countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
        }

        public Object getWarranty() {
            return warranty;
        }

        public void setWarranty(Object warranty) {
            this.warranty = warranty;
        }

        public Object getLegalDisclaimer() {
            return legalDisclaimer;
        }

        public void setLegalDisclaimer(Object legalDisclaimer) {
            this.legalDisclaimer = legalDisclaimer;
        }

        public Integer getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Integer createdBy) {
            this.createdBy = createdBy;
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


}
