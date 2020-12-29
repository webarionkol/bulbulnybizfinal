package com.apps.nybizz.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllVideoResponbse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




    public class Datum {

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
        private Object mediaUrl;
        @SerializedName("productname")
        @Expose
        private String productname;
        @SerializedName("productcode")
        @Expose
        private String productcode;
        @SerializedName("thumbnail")
        @Expose
        private Object thumbnail;
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
        private String variationDetails;
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

        public Object getMediaUrl() {
            return mediaUrl;
        }

        public void setMediaUrl(Object mediaUrl) {
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

        public Object getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(Object thumbnail) {
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

        public String getVariationDetails() {
            return variationDetails;
        }

        public void setVariationDetails(String variationDetails) {
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
