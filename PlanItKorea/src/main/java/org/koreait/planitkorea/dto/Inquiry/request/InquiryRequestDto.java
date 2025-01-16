package org.koreait.planitkorea.dto.Inquiry.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.planitkorea.entity.InquiryCategory;

@Data
@NoArgsConstructor
public class InquiryRequestDto {
    private String inquiryTitle;
    private InquiryCategory inquiryCategory;
    private String inquiryContent;
    private String inquiryImage;
}
