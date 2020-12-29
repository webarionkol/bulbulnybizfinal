package com.apps.nybizz.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailsresponse {
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
        @SerializedName("productname")
        @Expose
        private String productname;
        @SerializedName("productcode")
        @Expose
        private String productcode;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("mrp")
        @Expose
        private String mrp;
        @SerializedName("offer_price")
        @Expose
        private String offerPrice;
        @SerializedName("countryOfOrigin")
        @Expose
        private Object countryOfOrigin;
        @SerializedName("warranty")
        @Expose
        private Object warranty;
        @SerializedName("legalDisclaimer")
        @Expose
        private Object legalDisclaimer;
        @SerializedName("variation_details")
        @Expose
        private Object variationDetails;
        @SerializedName("video")
        @Expose
        private String video;
        @SerializedName("vendor")
        @Expose
        private Integer vendor;
        @SerializedName("category")
        @Expose
        private Integer category;
        @SerializedName("subcategory")
        @Expose
        private Integer subcategory;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getMrp() {
            return mrp;
        }

        public void setMrp(String mrp) {
            this.mrp = mrp;
        }

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
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

        public Object getVariationDetails() {
            return variationDetails;
        }

        public void setVariationDetails(Object variationDetails) {
            this.variationDetails = variationDetails;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public Integer getVendor() {
            return vendor;
        }

        public void setVendor(Integer vendor) {
            this.vendor = vendor;
        }

        public Integer getCategory() {
            return category;
        }

        public void setCategory(Integer category) {
            this.category = category;
        }

        public Integer getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(Integer subcategory) {
            this.subcategory = subcategory;
        }

    }
}
