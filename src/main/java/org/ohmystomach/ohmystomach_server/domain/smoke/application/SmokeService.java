package org.ohmystomach.ohmystomach_server.domain.smoke.application;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.ohmystomach.ohmystomach_server.domain.smoke.dao.SmokeRepository;
import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SmokeService {

    private final SmokeRepository smokeRepository;

    public ApiResponse<List<Smoke>> retrieveAllSmoke() {
        List<Smoke> smokeList = smokeRepository.findAll();
        if(smokeList.isEmpty()) {
            return ApiResponse.ok("흡연구역이 존재하지 않습니다.");
        }
        return ApiResponse.ok("흡연구역 목록을 성공적으로 조회했습니다.", smokeList);
    }

    public ApiResponse<Smoke> retrieveSmokeById(Long id) {
        // ID로 흡연구역을 조회, 없을 경우 예외 발생
        Optional<Smoke> optionalToilet = smokeRepository.findById(id);
        if(optionalToilet.isEmpty()){
            return ApiResponse.ok("흡연구역 데이터가 존재하지 않습니다.");
        }
        Smoke smoke = optionalToilet.get();
        return ApiResponse.ok("흡연구역 데이터를 성공적으로 조회했습니다.", smoke);
    }

//    public void pushData() {
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ohMyStomach", "root", "ansdlsqo0161");
//             CSVReader csvReader = new CSVReader(new FileReader("../../../global/data/중복제거.csv"))) {
//
//            String[] nextLine;
//
//            csvReader.readNext();
//
//            while ((nextLine = csvReader.readNext()) != null) {
//
//                Smoke smoke = new Smoke();
//                smoke.setDistrict(nextLine[0]);
//                smoke.setType(nextLine[1]);
//                smoke.setLocation(nextLine[2]);
////                smokes.add(smoke);
//                smokeRepository.save(smoke);
//            }
//
//
//        } catch (SQLException | CsvValidationException | IOException e) {
//            e.printStackTrace();
//        }
////
//
////        Path relativePath = Paths.get("..", "..", "..", "global/data/중복제거.csv");
////        String resourcePath = relativePath.toString();
//////        String resourcePath = "src/main/java/org/ohmystomach/ohmystomach_server/global/data/중복제거.csv";
//////        List<Smoke> smokes = new ArrayList<>();
////        try (Reader reader = new InputStreamReader(new ClassPathResource(resourcePath).getInputStream());
////             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
////
////            for (CSVRecord csvRecord : csvParser) {
////                Smoke smoke = new Smoke();
////                smoke.setDistrict(csvRecord.get("district"));
////                smoke.setType(csvRecord.get("type"));
////                smoke.setLocation(csvRecord.get("location"));
//////                smokes.add(smoke);
////                smokeRepository.save(smoke);
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
    public void pushData() {
        // 상대경로 설정: ../../../global/data/중복제거.csv
        Path relativePath = Paths.get("src", "main", "java", "org", "ohmystomach", "ohmystomach_server", "global", "data", "중복제거.csv");
        String csvFilePath = relativePath.toString();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ohmystomach-db-instance.c5wwk8ugsr8z.ap-northeast-2.rds.amazonaws.com:3306/ohmystomach_database", "OhMyStomachAdmin", "MyStomachOh0000");
             CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {

            String[] nextLine;
            csvReader.readNext(); // CSV 헤더 건너뛰기

            while ((nextLine = csvReader.readNext()) != null) {
                Smoke smoke = new Smoke();
                smoke.setDistrict(nextLine[0]);
                smoke.setType(nextLine[1]);
                smoke.setLocation(nextLine[2]);
                smokeRepository.save(smoke);
            }
        } catch (SQLException | CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }
}
