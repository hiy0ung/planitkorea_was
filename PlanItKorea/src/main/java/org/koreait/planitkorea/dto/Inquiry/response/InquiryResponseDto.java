package org.koreait.planitkorea.dto.Inquiry.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.planitkorea.entity.Inquiry;
import org.koreait.planitkorea.entity.InquiryCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryResponseDto {
    private String inquiryTitle;
    private InquiryCategory inquiryCategory;
    private String inquiryContent;
    private String inquiryImage;

    public InquiryResponseDto(Inquiry inquiry) {
        this.inquiryTitle = inquiry.getInquiryTitle();
        this.inquiryCategory = inquiry.getInquiryCategory();
        this.inquiryContent = inquiry.getInquiryContent();
        this.inquiryImage = inquiry.getInquiryImage();
    }
}
