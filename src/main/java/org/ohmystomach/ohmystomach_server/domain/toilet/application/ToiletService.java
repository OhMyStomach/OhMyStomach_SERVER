package org.ohmystomach.ohmystomach_server.domain.toilet.application;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.ohmystomach.ohmystomach_server.domain.smoke.domain.Smoke;
import org.ohmystomach.ohmystomach_server.global.common.response.ApiResponse;
import org.ohmystomach.ohmystomach_server.domain.toilet.dao.ToiletRepository;
import org.ohmystomach.ohmystomach_server.domain.toilet.domain.Toilet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
public class ToiletService {

  // ToiletRepository 의존성 주입, 데이터베이스와의 상호작용을 처리
  private final ToiletRepository toiletRepository;

  public ApiResponse<List<Toilet>> retrieveAllToilets() {
    // 모든 공중화장실 데이터를 데이터베이스에서 조회하여 반환
    List<Toilet> toiletList = toiletRepository.findAll();
    if(toiletList.isEmpty()) {
      return ApiResponse.ok("공중화장실이 존재하지 않습니다.");
    }
    return ApiResponse.ok("공중화장실 목록을 성공적으로 조회했습니다.", toiletList);
  }

  public ApiResponse<Toilet> retrieveToiletById(Long id) {
    // ID로 공중화장실을 조회, 없을 경우 예외 발생
    Optional<Toilet> optionalToilet = toiletRepository.findById(id);
    if(optionalToilet.isEmpty()){
      return ApiResponse.ok("공중화장실 데이터가 존재하지 않습니다.");
    }
    Toilet toilet = optionalToilet.get();
    return ApiResponse.ok("공중화장실 데이터를 성공적으로 조회했습니다.", toilet);
  }
  public void pushData() {
    // 상대경로 설정: ../../../global/data/중복제거.csv
    Path relativePath = Paths.get("src", "main", "java", "org", "ohmystomach", "ohmystomach_server", "global", "data", "서울시_공중화장실_위치정보.csv");
    String csvFilePath = relativePath.toString();

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://ohmystomach-db-instance.c5wwk8ugsr8z.ap-northeast-2.rds.amazonaws.com:3306/ohmystomach_database", "OhMyStomachAdmin", "MyStomachOh0000");
         CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {

      String[] nextLine;
      csvReader.readNext(); // CSV 헤더 건너뛰기

      while ((nextLine = csvReader.readNext()) != null) {
        Toilet toilet = new Toilet();
        toilet.setName(nextLine[0]);
        toilet.setType(nextLine[1]);
        toilet.setWsg84x(Double.valueOf(nextLine[2]));
        toilet.setWsg84y(Double.valueOf(nextLine[3]));
        toiletRepository.save(toilet);
      }
    } catch (SQLException | CsvValidationException | IOException e) {
      e.printStackTrace();
    }
  }
//  public void pushData() {
//    String resourcePath = "./global/data/서울시_공중화장실_위치정보.csv";
////    List<Toilet> toilets = new ArrayList<>();
//    try (Reader reader = new InputStreamReader(new ClassPathResource(resourcePath).getInputStream());
//         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
//
//      for (CSVRecord csvRecord : csvParser) {
//        Toilet toilet = new Toilet();
//        toilet.setName(csvRecord.get("name"));
//        toilet.setType(csvRecord.get("type"));
//        toilet.setWsg84x(Double.valueOf(csvRecord.get("WSG84X")));
//        toilet.setWsg84y(Double.valueOf(csvRecord.get("WSG84Y")));
////        toilets.add(toilet);
//        toiletRepository.save(toilet);
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
}
