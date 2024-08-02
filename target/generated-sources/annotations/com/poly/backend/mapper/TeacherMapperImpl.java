package com.poly.backend.mapper;

import com.poly.backend.dto.TeacherDTO;
import com.poly.backend.entity.Teacher;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-03T06:19:09+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public TeacherDTO toDto(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDTO.TeacherDTOBuilder teacherDTO = TeacherDTO.builder();

        teacherDTO.id( teacher.getId() );
        if ( teacher.getCreatedAt() != null ) {
            teacherDTO.createdAt( LocalDateTime.ofInstant( teacher.getCreatedAt().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        teacherDTO.createdBy( teacher.getCreatedBy() );
        if ( teacher.getUpdatedAt() != null ) {
            teacherDTO.updatedAt( LocalDateTime.ofInstant( teacher.getUpdatedAt().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        teacherDTO.updatedBy( teacher.getUpdatedBy() );
        teacherDTO.emailCaNhan( teacher.getEmailCaNhan() );
        teacherDTO.emailEdu( teacher.getEmailEdu() );
        teacherDTO.hinhDaiDien( teacher.getHinhDaiDien() );
        teacherDTO.hoGiangVien( teacher.getHoGiangVien() );
        teacherDTO.maGiangVien( teacher.getMaGiangVien() );
        teacherDTO.ngaySinh( teacher.getNgaySinh() );
        teacherDTO.tenGiangVien( teacher.getTenGiangVien() );
        teacherDTO.soDienThoai( teacher.getSoDienThoai() );

        return teacherDTO.build();
    }

    @Override
    public List<TeacherDTO> toDto(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherDTO> list = new ArrayList<TeacherDTO>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( toDto( teacher ) );
        }

        return list;
    }

    @Override
    public Teacher toEntity(TeacherDTO teacherDTO) {
        if ( teacherDTO == null ) {
            return null;
        }

        Teacher.TeacherBuilder teacher = Teacher.builder();

        teacher.maGiangVien( teacherDTO.getMaGiangVien() );
        teacher.emailCaNhan( teacherDTO.getEmailCaNhan() );
        teacher.emailEdu( teacherDTO.getEmailEdu() );
        teacher.hinhDaiDien( teacherDTO.getHinhDaiDien() );
        teacher.hoGiangVien( teacherDTO.getHoGiangVien() );
        teacher.ngaySinh( teacherDTO.getNgaySinh() );
        teacher.soDienThoai( teacherDTO.getSoDienThoai() );
        teacher.tenGiangVien( teacherDTO.getTenGiangVien() );

        return teacher.build();
    }

    @Override
    public void updateFromDto(TeacherDTO teacherDTO, Teacher teacher) {
        if ( teacherDTO == null ) {
            return;
        }

        teacher.setCreatedBy( teacherDTO.getCreatedBy() );
        teacher.setUpdatedBy( teacherDTO.getUpdatedBy() );
        if ( teacherDTO.getCreatedAt() != null ) {
            teacher.setCreatedAt( Date.from( teacherDTO.getCreatedAt().toInstant( ZoneOffset.UTC ) ) );
        }
        else {
            teacher.setCreatedAt( null );
        }
        if ( teacherDTO.getUpdatedAt() != null ) {
            teacher.setUpdatedAt( Date.from( teacherDTO.getUpdatedAt().toInstant( ZoneOffset.UTC ) ) );
        }
        else {
            teacher.setUpdatedAt( null );
        }
        teacher.setMaGiangVien( teacherDTO.getMaGiangVien() );
        teacher.setEmailCaNhan( teacherDTO.getEmailCaNhan() );
        teacher.setEmailEdu( teacherDTO.getEmailEdu() );
        teacher.setHinhDaiDien( teacherDTO.getHinhDaiDien() );
        teacher.setHoGiangVien( teacherDTO.getHoGiangVien() );
        teacher.setNgaySinh( teacherDTO.getNgaySinh() );
        teacher.setSoDienThoai( teacherDTO.getSoDienThoai() );
        teacher.setTenGiangVien( teacherDTO.getTenGiangVien() );
    }
}
