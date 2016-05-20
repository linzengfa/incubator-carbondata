/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.carbondata.spark.testsuite.allqueries

import java.io.File

import org.apache.spark.sql.Row
import org.apache.spark.sql.common.util.CarbonHiveContext._
import org.apache.spark.sql.common.util.QueryTest
import org.carbondata.core.util.CarbonProperties
import org.scalatest.BeforeAndAfterAll

/**
  * Test Class for all queries on multiple datatypes
  * Manohar
  */
class AllDataTypesTestCase6 extends QueryTest with BeforeAndAfterAll {

  override def beforeAll {

    val currentDirectory = new File(this.getClass.getResource("/").getPath + "/../../")
      .getCanonicalPath
    CarbonProperties.getInstance().addProperty("carbon.direct.surrogate","false")
    try {
      sql(
        "create cube Carbon_automation_test6 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string)  measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )"

      )
      sql("LOAD DATA FACT FROM '" + currentDirectory + "/src/test/resources/100_olap.csv' INTO Cube Carbon_automation_test6 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")

      sql(
        "create table hivetable(imei string,deviceInformationId int,MAC string,deviceColor " +
          "string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize " +
          "string,CUPAudit string,CPIClocked string,series string,productionDate timestamp," +
          "bomCode string,internalModels string, deliveryTime string, channelsId string, " +
          "channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince " +
          "string, deliveryCity string,deliveryDistrict string, deliveryStreet string, " +
          "oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry " +
          "string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet " +
          "string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, " +
          "Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, " +
          "Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber" +
          " string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, " +
          "Latest_YEAR int, Latest_MONTH int, Latest_DAY int, Latest_HOUR string, " +
          "Latest_areaId string, Latest_country string, Latest_province string, Latest_city " +
          "string, Latest_district string, Latest_street string, Latest_releaseId string, " +
          "Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, " +
          "Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string," +
          " Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, " +
          "Latest_phonePADPartitionedVersions string, Latest_operatorId string, " +
          "gamePointDescription string, gamePointId int,contractNumber int) row format " +
          "delimited fields terminated by ','"
      )


      sql(
        "LOAD DATA local inpath'" + currentDirectory + "/src/test/resources/100_olap.csv'" +
          " overwrite INTO table hivetable"
      )

      sql(
        "create cube myvmallTest dimensions(imei String,uuid String,MAC String,device_color String,device_shell_color String,device_name String,product_name String,ram String,rom String,cpu_clock String,series String,check_date String,check_month Integer ,check_day Integer,check_hour Integer,bom String,inside_name String,packing_date String,packing_year String,packing_month String,packing_day String,packing_hour String,customer_name String,deliveryAreaId String,deliveryCountry String,deliveryProvince String,deliveryCity String,deliveryDistrict String,packing_list_no String,order_no String,Active_check_time String,Active_check_year Integer,Active_check_month Integer,Active_check_day Integer,Active_check_hour Integer,ActiveAreaId String,ActiveCountry String,ActiveProvince String,Activecity String,ActiveDistrict String,Active_network String,Active_firmware_version String,Active_emui_version String,Active_os_version String,Latest_check_time String,Latest_check_year Integer,Latest_check_month Integer,Latest_check_day Integer,Latest_check_hour Integer,Latest_areaId String,Latest_country String,Latest_province String,Latest_city String,Latest_district String,Latest_firmware_version String,Latest_emui_version String,Latest_os_version String,Latest_network String,site String,site_desc String,product String,product_desc String) MEASURES(check_year Integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=3] )"
      )
      sql("LOAD DATA fact from '" + currentDirectory + "/src/test/resources/100_VMALL_1_Day_DATA_2015-09-15.csv' INTO CUBE myvmallTest PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER 'imei,uuid,MAC,device_color,device_shell_color,device_name,product_name,ram,rom,cpu_clock,series,check_date,check_year,check_month,check_day,check_hour,bom,inside_name,packing_date,packing_year,packing_month,packing_day,packing_hour,customer_name,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,packing_list_no,order_no,Active_check_time,Active_check_year,Active_check_month,Active_check_day,Active_check_hour,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,Active_network,Active_firmware_version,Active_emui_version,Active_os_version,Latest_check_time,Latest_check_year,Latest_check_month,Latest_check_day,Latest_check_hour,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_firmware_version,Latest_emui_version,Latest_os_version,Latest_network,site,site_desc,product,product_desc')")
      sql(
        "create cube IF NOT EXISTS traffic_2g_3g_4g dimensions(SOURCE_INFO String ,APP_CATEGORY_ID String ,APP_CATEGORY_NAME String ,APP_SUB_CATEGORY_ID String ,APP_SUB_CATEGORY_NAME String ,RAT_NAME String ,IMSI String ,OFFER_MSISDN String ,OFFER_ID String ,OFFER_OPTION_1 String ,OFFER_OPTION_2 String ,OFFER_OPTION_3 String ,MSISDN String ,PACKAGE_TYPE String ,PACKAGE_PRICE String ,TAG_IMSI String ,TAG_MSISDN String ,PROVINCE String ,CITY String ,AREA_CODE String ,TAC String ,IMEI String ,TERMINAL_TYPE String ,TERMINAL_BRAND String ,TERMINAL_MODEL String ,PRICE_LEVEL String ,NETWORK String ,SHIPPED_OS String ,WIFI String ,WIFI_HOTSPOT String ,GSM String ,WCDMA String ,TD_SCDMA String ,LTE_FDD String ,LTE_TDD String ,CDMA String ,SCREEN_SIZE String ,SCREEN_RESOLUTION String ,HOST_NAME String ,WEBSITE_NAME String ,OPERATOR String ,SRV_TYPE_NAME String ,TAG_HOST String ,CGI String ,CELL_NAME String ,COVERITY_TYPE1 String ,COVERITY_TYPE2 String ,COVERITY_TYPE3 String ,COVERITY_TYPE4 String ,COVERITY_TYPE5 String ,LATITUDE String ,LONGITUDE String ,AZIMUTH String ,TAG_CGI String ,APN String ,USER_AGENT String ,DAY String ,HOUR String ,`MIN` String ,IS_DEFAULT_BEAR integer ,EPS_BEARER_ID String ,QCI integer ,USER_FILTER String ,ANALYSIS_PERIOD String ) measures(UP_THROUGHPUT numeric,DOWN_THROUGHPUT numeric,UP_PKT_NUM numeric,DOWN_PKT_NUM numeric,APP_REQUEST_NUM numeric,PKT_NUM_LEN_1_64 numeric,PKT_NUM_LEN_64_128 numeric,PKT_NUM_LEN_128_256 numeric,PKT_NUM_LEN_256_512 numeric,PKT_NUM_LEN_512_768 numeric,PKT_NUM_LEN_768_1024 numeric,PKT_NUM_LEN_1024_ALL numeric,IP_FLOW_MARK numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (MSISDN) ,PARTITION_COUNT=3] )"
      )
      sql("LOAD DATA fact from '" + currentDirectory + "/src/test/resources/FACT_UNITED_DATA_INFO_sample_cube.csv' INTO CUBE traffic_2g_3g_4g PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")

      sql(
        "CREATE CUBE cube_restructure444 DIMENSIONS (a0 STRING,a STRING) MEASURES(b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )"

      )
      sql("LOAD DATA FACT FROM '" + currentDirectory + "/src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure444 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"')")

      sql(
        "create cube  Carbon_automation_vmall_test1 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string)  measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )"

      )
      sql("LOAD DATA FACT FROM '" + currentDirectory + "/src/test/resources/Vmall_100_olap.csv' INTO Cube Carbon_automation_vmall_test1 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')");
      sql(
        "create cube Carbon_automation_test5 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string)  measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )"

      )
      sql("LOAD DATA FACT FROM '" + currentDirectory + "/src/test/resources/100_olap.csv' INTO Cube Carbon_automation_test5 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')");

      sql("create schema myschema")
      sql("create schema myschema1")
      sql("create schema drug")
    }catch {
      case e : Exception => print("ERROR: DROP Carbon_automation_test6 ")
    }
  }

  override def afterAll {
    try {
      sql("drop cube Carbon_automation_test6")
      sql("drop table hivetable")
      sql("drop cube myvmallTest")
      sql("drop cube traffic_2g_3g_4g")
      sql("drop cube cube_restructure444")
      sql("drop cube Carbon_automation_vmall_test1")
      sql("drop cube Carbon_automation_test5")
      sql("drop schema myschema")
      sql("drop schema myschema1")
      sql("drop schema drug")
    }catch {
      case e : Exception => print("ERROR: DROP Carbon_automation_test6 ")
    }
  }

  //AllDataTypesTestCases2

  //Test-46
  test("select sum(deviceinformationid)+10 as a ,series  from Carbon_automation_test6 group by series.")({

    checkAnswer(
      sql("select sum(deviceinformationid)+10 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(900451, "6Series"), Row(1410606, "0Series"), Row(800391, "4Series"), Row(920401, "8Series"), Row(1620257, "7Series"), Row(300060, "1Series"), Row(1321584, "5Series"), Row(800323, "9Series"), Row(710330, "3Series"), Row(810414, "2Series")))
  })



  //Test-48
  test("select sum(latest_year)+10 as a ,series  from Carbon_automation_test6 group by series")({

    checkAnswer(
      sql("select sum(latest_year)+10 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(18145, "6Series"), Row(30235, "0Series"), Row(16130, "4Series"), Row(22175, "8Series"), Row(22175, "7Series"), Row(6055, "1Series"), Row(34265, "5Series"), Row(16130, "9Series"), Row(16130, "3Series"), Row(18145, "2Series")))
  })

  //Test-49
  test("select sum(deviceinformationid)+10.32 as a ,series  from Carbon_automation_test6 group by series")({

    checkAnswer(
      sql("select sum(deviceinformationid)+10.32 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(900451.32, "6Series"), Row(1410606.32, "0Series"), Row(800391.32, "4Series"), Row(920401.32, "8Series"), Row(1620257.32, "7Series"), Row(300060.32, "1Series"), Row(1321584.32, "5Series"), Row(800323.32, "9Series"), Row(710330.32, "3Series"), Row(810414.32, "2Series")))
  })



  //TC_051
  test("select sum(latest_year)+10.364 as a,series  from Carbon_automation_test6 group by series")({
    checkAnswer(
      sql("select sum(latest_year)+10.364 as a,series  from Carbon_automation_test6 group by series"),
      Seq(Row(18145.364, "6Series"), Row(30235.364, "0Series"), Row(16130.364, "4Series"), Row(22175.364, "8Series"), Row(22175.364, "7Series"), Row(6055.364, "1Series"), Row(34265.364, "5Series"), Row(16130.364, "9Series"), Row(16130.364, "3Series"), Row(18145.364, "2Series")))
  })

  //TC_052
  test("select avg(deviceinformationid)+10 as a ,series  from Carbon_automation_test6 group by series")({
    checkAnswer(
      sql("select avg(deviceinformationid)+10 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(100059.0, "6Series"), Row(94049.73333333334, "0Series"), Row(100057.625, "4Series"), Row(83681.90909090909, "8Series"), Row(147305.18181818182, "7Series"), Row(100026.66666666667, "1Series"), Row(77749.64705882352, "5Series"), Row(100049.125, "9Series"), Row(88800.0, "3Series"), Row(90054.88888888889, "2Series")))
  })

  //TC_054
  test("select avg(latest_year)+10 as a ,series  from Carbon_automation_test6 group by series")({
    checkAnswer(
      sql("select avg(latest_year)+10 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(2025.0, "6Series"), Row(2025.0, "0Series"), Row(2025.0, "4Series"), Row(2025.0, "8Series"), Row(2025.0, "7Series"), Row(2025.0, "1Series"), Row(2025.0, "5Series"), Row(2025.0, "9Series"), Row(2025.0, "3Series"), Row(2025.0, "2Series")))
  })


  //TC_072
  test("select sum(deviceInformationId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum(deviceInformationId) a  from Carbon_automation_test6"),
      Seq(Row(9594717)))
  })

  //TC_073
  test("select sum(channelsId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum(channelsId) a  from Carbon_automation_test6"),
      Seq(Row(428.0)))
  })

  //TC_074
  test("select sum(bomCode)  a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum(bomCode)  a  from Carbon_automation_test6"),
      Seq(Row(9594717.0)))
  })

  //TC_075
  test("select sum(Latest_MONTH)  a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum(Latest_MONTH)  a  from Carbon_automation_test6"),
      Seq(Row(693)))
  })

  //TC_078
  test("select sum( DISTINCT channelsId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum( DISTINCT channelsId) a  from Carbon_automation_test6"),
      Seq(Row(428.0)))
  })

  //TC_083
  test("select avg(deviceInformationId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select avg(deviceInformationId) a  from Carbon_automation_test6"),
      Seq(Row(96916.33333333333)))
  })

  //TC_084
  test("select avg(channelsId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select avg(channelsId) a  from Carbon_automation_test6"),
      Seq(Row(4.3232323232323235)))
  })

  //TC_085
  test("select avg(bomCode)  a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select avg(bomCode)  a  from Carbon_automation_test6"),
      Seq(Row(96916.33333333333)))
  })

  //TC_086
  test("select avg(Latest_MONTH)  a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select avg(Latest_MONTH)  a  from Carbon_automation_test6"),
      Seq(Row(7.0)))
  })


  //TC_104
  test("select var_pop(deviceInformationId)  as a from Carbon_automation_test6")({
    checkAnswer(
      sql("select var_pop(deviceInformationId)  as a from Carbon_automation_test6"),
      Seq(Row(9.310415559636362E9)))
  })


  //TC_114
  test("select percentile_approx(deviceInformationId,0.2,5) as a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select percentile_approx(deviceInformationId,0.2,5) as a  from Carbon_automation_test6"),
      Seq(Row(17207.04)))
  })


  //TC_119
  test("select variance(gamePointId) as a   from Carbon_automation_test6")({
    checkAnswer(
      sql("select variance(gamePointId) as a   from Carbon_automation_test6"),
      Seq(Row(654787.843930927)))
  })

  //TC_120
  test("select var_pop(gamePointId)  as a from Carbon_automation_test6")({
    checkAnswer(
      sql("select var_pop(gamePointId)  as a from Carbon_automation_test6"),
      Seq(Row(654787.8439309275)))
  })

  //TC_121
  test("select var_samp(gamePointId) as a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select var_samp(gamePointId) as a  from Carbon_automation_test6"),
      Seq(Row(661469.3525424675)))
  })

  //TC_123
  test("select stddev_samp(gamePointId)  as a from Carbon_automation_test6")({
    checkAnswer(
      sql("select stddev_samp(gamePointId)  as a from Carbon_automation_test6"),
      Seq(Row(813.3076616769742)))
  })

  //TC_126
  test("select corr(gamePointId,gamePointId)  as a from Carbon_automation_test6")({
    checkAnswer(
      sql("select corr(gamePointId,gamePointId)  as a from Carbon_automation_test6"),
      Seq(Row(0.9999999999999999)))
  })


  //TC_130
  test("select percentile_approx(gamePointId,0.2,5) as a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select percentile_approx(gamePointId,0.2,5) as a  from Carbon_automation_test6"),
      Seq(Row(492.5739612188367)))
  })



  //TC_135
  test("select FIRST(imei) a from Carbon_automation_test6")({
    checkAnswer(
      sql("select FIRST(imei) a from Carbon_automation_test6"),
      Seq(Row("1AA100040")))
  })


  //TC_137
  test("select series,count(imei) a from Carbon_automation_test6 group by series order by a")({
    checkAnswer(
      sql("select series,count(imei) a from Carbon_automation_test6 group by series order by a"),
      Seq(Row("1Series", 3), Row("4Series", 8), Row("9Series", 8), Row("3Series", 8), Row("6Series", 9), Row("2Series", 9), Row("7Series", 11), Row("8Series", 11), Row("0Series", 15), Row("5Series", 17)))
  })

  //TC_143
  test("select deliveryProvince,series,sum(deviceInformationId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,sum(deviceInformationId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 600310), Row("Guangdong Province", "1Series", 100032), Row("Guangdong Province", "2Series", 200085), Row("Guangdong Province", "3Series", 410217), Row("Guangdong Province", "4Series", 100059), Row("Guangdong Province", "5Series", 520235), Row("Guangdong Province", "6Series", 100038), Row("Guangdong Province", "7Series", 110054), Row("Guangdong Province", "8Series", 300151), Row("Guangdong Province", "9Series", 200123), Row("Hubei Province", "0Series", 210087), Row("Hubei Province", "1Series", 100005), Row("Hubei Province", "2Series", 400244), Row("Hubei Province", "3Series", 100077), Row("Hubei Province", "4Series", 200071), Row("Hubei Province", "5Series", 1000), Row("Hubei Province", "6Series", 200130), Row("Hubei Province", "7Series", 1300123), Row("Hubei Province", "8Series", 300091), Row("Hubei Province", "9Series", 200062), Row("Hunan Province", "0Series", 600199), Row("Hunan Province", "1Series", 100013), Row("Hunan Province", "2Series", 210075), Row("Hunan Province", "3Series", 200026), Row("Hunan Province", "4Series", 500251), Row("Hunan Province", "5Series", 800339), Row("Hunan Province", "6Series", 600273), Row("Hunan Province", "7Series", 210070), Row("Hunan Province", "8Series", 320149), Row("Hunan Province", "9Series", 400128)))
  })

  //TC_144
  test("select deliveryProvince,series,sum(channelsId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,sum(channelsId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 20.0), Row("Guangdong Province", "1Series", 7.0), Row("Guangdong Province", "2Series", 13.0), Row("Guangdong Province", "3Series", 21.0), Row("Guangdong Province", "4Series", 7.0), Row("Guangdong Province", "5Series", 25.0), Row("Guangdong Province", "6Series", 3.0), Row("Guangdong Province", "7Series", 3.0), Row("Guangdong Province", "8Series", 10.0), Row("Guangdong Province", "9Series", 12.0), Row("Hubei Province", "0Series", 15.0), Row("Hubei Province", "1Series", 1.0), Row("Hubei Province", "2Series", 12.0), Row("Hubei Province", "3Series", 6.0), Row("Hubei Province", "4Series", 9.0), Row("Hubei Province", "5Series", 3.0), Row("Hubei Province", "6Series", 12.0), Row("Hubei Province", "7Series", 29.0), Row("Hubei Province", "8Series", 17.0), Row("Hubei Province", "9Series", 12.0), Row("Hunan Province", "0Series", 29.0), Row("Hunan Province", "1Series", 6.0), Row("Hunan Province", "2Series", 16.0), Row("Hunan Province", "3Series", 9.0), Row("Hunan Province", "4Series", 18.0), Row("Hunan Province", "5Series", 44.0), Row("Hunan Province", "6Series", 28.0), Row("Hunan Province", "7Series", 13.0), Row("Hunan Province", "8Series", 15.0), Row("Hunan Province", "9Series", 13.0)))
  })

  //TC_145
  test("select deliveryProvince,series,sum(bomCode) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,sum(bomCode) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 600310.0), Row("Guangdong Province", "1Series", 100032.0), Row("Guangdong Province", "2Series", 200085.0), Row("Guangdong Province", "3Series", 410217.0), Row("Guangdong Province", "4Series", 100059.0), Row("Guangdong Province", "5Series", 520235.0), Row("Guangdong Province", "6Series", 100038.0), Row("Guangdong Province", "7Series", 110054.0), Row("Guangdong Province", "8Series", 300151.0), Row("Guangdong Province", "9Series", 200123.0), Row("Hubei Province", "0Series", 210087.0), Row("Hubei Province", "1Series", 100005.0), Row("Hubei Province", "2Series", 400244.0), Row("Hubei Province", "3Series", 100077.0), Row("Hubei Province", "4Series", 200071.0), Row("Hubei Province", "5Series", 1000.0), Row("Hubei Province", "6Series", 200130.0), Row("Hubei Province", "7Series", 1300123.0), Row("Hubei Province", "8Series", 300091.0), Row("Hubei Province", "9Series", 200062.0), Row("Hunan Province", "0Series", 600199.0), Row("Hunan Province", "1Series", 100013.0), Row("Hunan Province", "2Series", 210075.0), Row("Hunan Province", "3Series", 200026.0), Row("Hunan Province", "4Series", 500251.0), Row("Hunan Province", "5Series", 800339.0), Row("Hunan Province", "6Series", 600273.0), Row("Hunan Province", "7Series", 210070.0), Row("Hunan Province", "8Series", 320149.0), Row("Hunan Province", "9Series", 400128.0)))
  })


  //TC_148
  test("select deliveryProvince,series,avg(deviceInformationId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,avg(deviceInformationId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 100051.66666666667), Row("Guangdong Province", "1Series", 100032.0), Row("Guangdong Province", "2Series", 100042.5), Row("Guangdong Province", "3Series", 82043.4), Row("Guangdong Province", "4Series", 100059.0), Row("Guangdong Province", "5Series", 74319.28571428571), Row("Guangdong Province", "6Series", 100038.0), Row("Guangdong Province", "7Series", 55027.0), Row("Guangdong Province", "8Series", 100050.33333333333), Row("Guangdong Province", "9Series", 100061.5), Row("Hubei Province", "0Series", 70029.0), Row("Hubei Province", "1Series", 100005.0), Row("Hubei Province", "2Series", 100061.0), Row("Hubei Province", "3Series", 100077.0), Row("Hubei Province", "4Series", 100035.5), Row("Hubei Province", "5Series", 1000.0), Row("Hubei Province", "6Series", 100065.0), Row("Hubei Province", "7Series", 216687.16666666666), Row("Hubei Province", "8Series", 100030.33333333333), Row("Hubei Province", "9Series", 100031.0), Row("Hunan Province", "0Series", 100033.16666666667), Row("Hunan Province", "1Series", 100013.0), Row("Hunan Province", "2Series", 70025.0), Row("Hunan Province", "3Series", 100013.0), Row("Hunan Province", "4Series", 100050.2), Row("Hunan Province", "5Series", 88926.55555555556), Row("Hunan Province", "6Series", 100045.5), Row("Hunan Province", "7Series", 70023.33333333333), Row("Hunan Province", "8Series", 64029.8), Row("Hunan Province", "9Series", 100032.0)))
  })

  //TC_149
  test("select deliveryProvince,series,avg(channelsId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,avg(channelsId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 3.3333333333333335), Row("Guangdong Province", "1Series", 7.0), Row("Guangdong Province", "2Series", 6.5), Row("Guangdong Province", "3Series", 4.2), Row("Guangdong Province", "4Series", 7.0), Row("Guangdong Province", "5Series", 3.5714285714285716), Row("Guangdong Province", "6Series", 3.0), Row("Guangdong Province", "7Series", 1.5), Row("Guangdong Province", "8Series", 3.3333333333333335), Row("Guangdong Province", "9Series", 6.0), Row("Hubei Province", "0Series", 5.0), Row("Hubei Province", "1Series", 1.0), Row("Hubei Province", "2Series", 3.0), Row("Hubei Province", "3Series", 6.0), Row("Hubei Province", "4Series", 4.5), Row("Hubei Province", "5Series", 3.0), Row("Hubei Province", "6Series", 6.0), Row("Hubei Province", "7Series", 4.833333333333333), Row("Hubei Province", "8Series", 5.666666666666667), Row("Hubei Province", "9Series", 6.0), Row("Hunan Province", "0Series", 4.833333333333333), Row("Hunan Province", "1Series", 6.0), Row("Hunan Province", "2Series", 5.333333333333333), Row("Hunan Province", "3Series", 4.5), Row("Hunan Province", "4Series", 3.6), Row("Hunan Province", "5Series", 4.888888888888889), Row("Hunan Province", "6Series", 4.666666666666667), Row("Hunan Province", "7Series", 4.333333333333333), Row("Hunan Province", "8Series", 3.0), Row("Hunan Province", "9Series", 3.25)))
  })

  //TC_150
  test("select deliveryProvince,series,avg(bomCode) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,avg(bomCode) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 100051.66666666667), Row("Guangdong Province", "1Series", 100032.0), Row("Guangdong Province", "2Series", 100042.5), Row("Guangdong Province", "3Series", 82043.4), Row("Guangdong Province", "4Series", 100059.0), Row("Guangdong Province", "5Series", 74319.28571428571), Row("Guangdong Province", "6Series", 100038.0), Row("Guangdong Province", "7Series", 55027.0), Row("Guangdong Province", "8Series", 100050.33333333333), Row("Guangdong Province", "9Series", 100061.5), Row("Hubei Province", "0Series", 70029.0), Row("Hubei Province", "1Series", 100005.0), Row("Hubei Province", "2Series", 100061.0), Row("Hubei Province", "3Series", 100077.0), Row("Hubei Province", "4Series", 100035.5), Row("Hubei Province", "5Series", 1000.0), Row("Hubei Province", "6Series", 100065.0), Row("Hubei Province", "7Series", 216687.16666666666), Row("Hubei Province", "8Series", 100030.33333333333), Row("Hubei Province", "9Series", 100031.0), Row("Hunan Province", "0Series", 100033.16666666667), Row("Hunan Province", "1Series", 100013.0), Row("Hunan Province", "2Series", 70025.0), Row("Hunan Province", "3Series", 100013.0), Row("Hunan Province", "4Series", 100050.2), Row("Hunan Province", "5Series", 88926.55555555556), Row("Hunan Province", "6Series", 100045.5), Row("Hunan Province", "7Series", 70023.33333333333), Row("Hunan Province", "8Series", 64029.8), Row("Hunan Province", "9Series", 100032.0)))
  })

  //TC_161
  test("select Latest_DAY from Carbon_automation_test6 where Latest_DAY in (select  Latest_DAY from Carbon_automation_test6) order by Latest_DAY")({
    checkAnswer(
      sql("select Latest_DAY from Carbon_automation_test6 where Latest_DAY in (select  Latest_DAY from Carbon_automation_test6) order by Latest_DAY"),
      Seq(Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1), Row(1)))
  })



  //TC_164
  test("select channelsId from Carbon_automation_test6 where channelsId in (select  channelsId from Carbon_automation_test6) order by channelsId")({
    checkAnswer(
      sql("select channelsId from Carbon_automation_test6 where channelsId in (select  channelsId from Carbon_automation_test6) order by channelsId"),
      Seq(Row("1"), Row("1"), Row("1"), Row("1"), Row("1"), Row("1"), Row("1"), Row("1"), Row("1"), Row("1"), Row("1"), Row("1"), Row("2"), Row("2"), Row("2"), Row("2"), Row("2"), Row("2"), Row("2"), Row("2"), Row("2"), Row("2"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("3"), Row("4"), Row("4"), Row("4"), Row("4"), Row("4"), Row("4"), Row("4"), Row("4"), Row("4"), Row("4"), Row("4"), Row("4"), Row("5"), Row("5"), Row("5"), Row("5"), Row("5"), Row("5"), Row("5"), Row("5"), Row("5"), Row("5"), Row("5"), Row("5"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("6"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7"), Row("7")))
  })

  //TC_165
  test("select  imei, sum(deviceInformationId) as a  from Carbon_automation_test6 where deviceInformationId in (select deviceInformationId  from Carbon_automation_test6) group by deviceInformationId,imei")({
    checkAnswer(
      sql("select  imei, sum(deviceInformationId) as a  from Carbon_automation_test6 where deviceInformationId in (select deviceInformationId  from Carbon_automation_test6) group by deviceInformationId,imei"),
      Seq(Row("1AA100082", 100082), Row("1AA1000", 1000), Row("1AA1000000", 1000000), Row("1AA100008", 100008), Row("1AA100018", 100018), Row("1AA100028", 100028), Row("1AA100038", 100038), Row("1AA100048", 100048), Row("1AA100058", 100058), Row("1AA100068", 100068), Row("1AA100078", 100078), Row("1AA100003", 100003), Row("1AA10004", 10004), Row("1AA100013", 100013), Row("1AA100023", 100023), Row("1AA100033", 100033), Row("1AA100043", 100043), Row("1AA100053", 100053), Row("1AA100063", 100063), Row("1AA100073", 100073), Row("1AA100083", 100083), Row("1AA100", 100), Row("1AA100009", 100009), Row("1AA100019", 100019), Row("1AA100029", 100029), Row("1AA100039", 100039), Row("1AA100049", 100049), Row("1AA100059", 100059), Row("1AA100069", 100069), Row("1AA100079", 100079), Row("1AA100004", 100004), Row("1AA10005", 10005), Row("1AA100014", 100014), Row("1AA100024", 100024), Row("1AA100034", 100034), Row("1AA100044", 100044), Row("1AA100054", 100054), Row("1AA100064", 100064), Row("1AA100074", 100074), Row("1AA100084", 100084), Row("1AA10000", 10000), Row("1AA1", 1), Row("1AA10006", 10006), Row("1AA100005", 100005), Row("1AA100015", 100015), Row("1AA100025", 100025), Row("1AA100035", 100035), Row("1AA100045", 100045), Row("1AA100055", 100055), Row("1AA100065", 100065), Row("1AA100075", 100075), Row("1AA100000", 100000), Row("1AA10001", 10001), Row("1AA100010", 100010), Row("1AA100020", 100020), Row("1AA100030", 100030), Row("1AA100040", 100040), Row("1AA100050", 100050), Row("1AA100060", 100060), Row("1AA100070", 100070), Row("1AA100080", 100080), Row("1AA100006", 100006), Row("1AA10007", 10007), Row("1AA100016", 100016), Row("1AA100026", 100026), Row("1AA100036", 100036), Row("1AA100046", 100046), Row("1AA100056", 100056), Row("1AA100066", 100066), Row("1AA10", 10), Row("1AA100076", 100076), Row("1AA10002", 10002), Row("1AA100001", 100001), Row("1AA100011", 100011), Row("1AA100021", 100021), Row("1AA100031", 100031), Row("1AA100041", 100041), Row("1AA100051", 100051), Row("1AA100061", 100061), Row("1AA100071", 100071), Row("1AA100081", 100081), Row("1AA10008", 10008), Row("1AA100007", 100007), Row("1AA100017", 100017), Row("1AA100027", 100027), Row("1AA100037", 100037), Row("1AA100047", 100047), Row("1AA100057", 100057), Row("1AA100067", 100067), Row("1AA100077", 100077), Row("1AA100002", 100002), Row("1AA10003", 10003), Row("1AA100012", 100012), Row("1AA100022", 100022), Row("1AA100032", 100032), Row("1AA100042", 100042), Row("1AA100052", 100052), Row("1AA100062", 100062), Row("1AA100072", 100072)))
  })

  //TC_170
  test("select Upper(series) a ,channelsId from Carbon_automation_test6 group by series,channelsId order by channelsId")({
    checkAnswer(
      sql("select Upper(series) a ,channelsId from Carbon_automation_test6 group by series,channelsId order by channelsId"),
      Seq(Row("0SERIES", "1"), Row("2SERIES", "1"), Row("8SERIES", "1"), Row("3SERIES", "1"), Row("7SERIES", "1"), Row("4SERIES", "1"), Row("1SERIES", "1"), Row("5SERIES", "1"), Row("9SERIES", "1"), Row("8SERIES", "2"), Row("7SERIES", "2"), Row("0SERIES", "2"), Row("4SERIES", "2"), Row("5SERIES", "2"), Row("6SERIES", "2"), Row("3SERIES", "3"), Row("7SERIES", "3"), Row("0SERIES", "3"), Row("5SERIES", "3"), Row("2SERIES", "3"), Row("8SERIES", "3"), Row("9SERIES", "3"), Row("6SERIES", "3"), Row("3SERIES", "4"), Row("5SERIES", "4"), Row("8SERIES", "4"), Row("6SERIES", "4"), Row("7SERIES", "4"), Row("0SERIES", "4"), Row("0SERIES", "5"), Row("6SERIES", "5"), Row("3SERIES", "5"), Row("2SERIES", "5"), Row("8SERIES", "5"), Row("5SERIES", "5"), Row("3SERIES", "6"), Row("0SERIES", "6"), Row("4SERIES", "6"), Row("5SERIES", "6"), Row("2SERIES", "6"), Row("9SERIES", "6"), Row("6SERIES", "6"), Row("7SERIES", "6"), Row("1SERIES", "6"), Row("8SERIES", "6"), Row("7SERIES", "7"), Row("0SERIES", "7"), Row("1SERIES", "7"), Row("2SERIES", "7"), Row("8SERIES", "7"), Row("6SERIES", "7"), Row("4SERIES", "7"), Row("5SERIES", "7")))
  })


  //TC_190
  test("select series,gamePointId as a from Carbon_automation_test6  order by series asc limit 10")({
    checkAnswer(
      sql("select series,gamePointId as a from Carbon_automation_test6  order by series asc limit 10"),
      Seq(Row("0Series", 901.0), Row("0Series", 2972.0), Row("0Series", 1841.0), Row("0Series", 1341.0), Row("0Series", 505.0), Row("0Series", 1778.0), Row("0Series", 1724.0), Row("0Series", 2436.0), Row("0Series", 202.0), Row("0Series", 2890.0)))
  })

  //TC_191
  test("select series,gamePointId as a from Carbon_automation_test6  order by series desc limit 10")({
    checkAnswer(
      sql("select series,gamePointId as a from Carbon_automation_test6  order by series desc limit 10"),
      Seq(Row("9Series", 1991.0), Row("9Series", 954.0), Row("9Series", 136.0), Row("9Series", 2288.0), Row("9Series", 571.0), Row("9Series", 2952.0), Row("9Series", 1823.0), Row("9Series", 2205.0), Row("8Series", 412.0), Row("8Series", 1697.0)))
  })

  //TC_209
  test("select * from (select if( Latest_areaId=3,NULL,Latest_areaId) as babu,NULL a from Carbon_automation_test6) qq where babu<=>a")({
    checkAnswer(
      sql("select * from (select if( Latest_areaId=3,NULL,Latest_areaId) as babu,NULL a from Carbon_automation_test6) qq where babu<=>a"),
      Seq(Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null), Row("null", null)))
  })


  //TC_233
  test("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR channelsId <=1 and series='7Series'")({
    checkAnswer(
      sql("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR channelsId <=1 and series='7Series'"),
      Seq(Row("1AA10000", 2175.0, "1", "7Series"), Row("1AA100031", 1080.0, "1", "7Series")))
  })

  //TC_234
  test("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR channelsId <=1 or series='7Series'")({
    checkAnswer(
      sql("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR channelsId <=1 or series='7Series'"),
      Seq(Row("1AA1", 2738.562, "4", "7Series"), Row("1AA10", 1714.635, "4", "7Series"), Row("1AA10000", 2175.0, "1", "7Series"), Row("1AA1000000", 1600.0, "6", "7Series"), Row("1AA100005", 2593.0, "1", "1Series"), Row("1AA100008", 1442.0, "1", "8Series"), Row("1AA100011", 202.0, "1", "0Series"), Row("1AA100015", 2863.0, "1", "4Series"), Row("1AA100025", 1724.0, "1", "0Series"), Row("1AA100026", 1768.0, "7", "7Series"), Row("1AA10003", 2071.0, "4", "7Series"), Row("1AA100030", 1333.0, "3", "7Series"), Row("1AA100031", 1080.0, "1", "7Series"), Row("1AA100037", 1015.0, "6", "7Series"), Row("1AA100039", 1750.0, "1", "8Series"), Row("1AA100041", 2734.0, "1", "5Series"), Row("1AA100047", 1823.0, "1", "9Series"), Row("1AA100048", 2399.0, "1", "3Series"), Row("1AA100050", 29.0, "1", "2Series"), Row("1AA100054", 1368.0, "2", "7Series"), Row("1AA100055", 1728.0, "7", "7Series")))
  })

  //TC_235
  test("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR (channelsId <=1 and series='1Series')")({
    checkAnswer(
      sql("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR (channelsId <=1 and series='1Series')"),
      Seq(Row("1AA100005", 2593.0, "1", "1Series")))
  })

  //TC_236
  test("select sum(gamePointId) a from Carbon_automation_test6 where channelsId >=10 OR (channelsId <=1 and series='1Series')")({
    checkAnswer(
      sql("select sum(gamePointId) a from Carbon_automation_test6 where channelsId >=10 OR (channelsId <=1 and series='1Series')"),
      Seq(Row(2593.0)))
  })

  //TC_237
  test("select * from (select imei,if(imei='1AA100060',NULL,imei) a from Carbon_automation_test6) aa  where a IS NULL")({
    checkAnswer(
      sql("select * from (select imei,if(imei='1AA100060',NULL,imei) a from Carbon_automation_test6) aa  where a IS NULL"),
      Seq(Row("1AA100060", "null")))
  })


  //TC_280
  test("SELECT Activecity, imei, ActiveCountry, ActiveDistrict FROM (select * from Carbon_automation_test6) SUB_QRY ORDER BY ActiveCountry ASC, ActiveDistrict ASC, Activecity ASC")({
    checkAnswer(
      sql("SELECT Activecity, imei, ActiveCountry, ActiveDistrict FROM (select * from Carbon_automation_test6) SUB_QRY ORDER BY ActiveCountry ASC, ActiveDistrict ASC, Activecity ASC"),
      Seq(Row("wuhan", "1AA10", "Chinese", "hongshan"), Row("wuhan", "1AA100001", "Chinese", "hongshan"), Row("wuhan", "1AA100004", "Chinese", "hongshan"), Row("wuhan", "1AA100008", "Chinese", "hongshan"), Row("wuhan", "1AA100024", "Chinese", "hongshan"), Row("wuhan", "1AA100025", "Chinese", "hongshan"), Row("wuhan", "1AA100039", "Chinese", "hongshan"), Row("wuhan", "1AA100045", "Chinese", "hongshan"), Row("wuhan", "1AA100046", "Chinese", "hongshan"), Row("wuhan", "1AA100047", "Chinese", "hongshan"), Row("wuhan", "1AA100056", "Chinese", "hongshan"), Row("wuhan", "1AA100058", "Chinese", "hongshan"), Row("wuhan", "1AA10006", "Chinese", "hongshan"), Row("wuhan", "1AA100061", "Chinese", "hongshan"), Row("wuhan", "1AA100070", "Chinese", "hongshan"), Row("wuhan", "1AA100078", "Chinese", "hongshan"), Row("shenzhen", "1AA100013", "Chinese", "longgang"), Row("shenzhen", "1AA100020", "Chinese", "longgang"), Row("shenzhen", "1AA100028", "Chinese", "longgang"), Row("shenzhen", "1AA100032", "Chinese", "longgang"), Row("shenzhen", "1AA100035", "Chinese", "longgang"), Row("shenzhen", "1AA10004", "Chinese", "longgang"), Row("shenzhen", "1AA100044", "Chinese", "longgang"), Row("shenzhen", "1AA100054", "Chinese", "longgang"), Row("shenzhen", "1AA100060", "Chinese", "longgang"), Row("shenzhen", "1AA100073", "Chinese", "longgang"), Row("shenzhen", "1AA100074", "Chinese", "longgang"), Row("shenzhen", "1AA100083", "Chinese", "longgang"), Row("guangzhou", "1AA100055", "Chinese", "longhua"), Row("guangzhou", "1AA100075", "Chinese", "longhua"), Row("guangzhou", "1AA1", "Chinese", "longhua"), Row("guangzhou", "1AA100010", "Chinese", "longhua"), Row("guangzhou", "1AA100017", "Chinese", "longhua"), Row("guangzhou", "1AA100022", "Chinese", "longhua"), Row("guangzhou", "1AA100026", "Chinese", "longhua"), Row("guangzhou", "1AA100030", "Chinese", "longhua"), Row("zhuzhou", "1AA100002", "Chinese", "tianyuan"), Row("zhuzhou", "1AA100003", "Chinese", "tianyuan"), Row("zhuzhou", "1AA100027", "Chinese", "tianyuan"), Row("zhuzhou", "1AA100042", "Chinese", "tianyuan"), Row("zhuzhou", "1AA10005", "Chinese", "tianyuan"), Row("zhuzhou", "1AA100052", "Chinese", "tianyuan"), Row("zhuzhou", "1AA100053", "Chinese", "tianyuan"), Row("zhuzhou", "1AA100062", "Chinese", "tianyuan"), Row("zhuzhou", "1AA100063", "Chinese", "tianyuan"), Row("zhuzhou", "1AA100081", "Chinese", "tianyuan"), Row("xiangtan", "1AA10000", "Chinese", "xiangtan"), Row("xiangtan", "1AA1000000", "Chinese", "xiangtan"), Row("xiangtan", "1AA100007", "Chinese", "xiangtan"), Row("xiangtan", "1AA100009", "Chinese", "xiangtan"), Row("xiangtan", "1AA10001", "Chinese", "xiangtan"), Row("xiangtan", "1AA100011", "Chinese", "xiangtan"), Row("xiangtan", "1AA100012", "Chinese", "xiangtan"), Row("xiangtan", "1AA100016", "Chinese", "xiangtan"), Row("xiangtan", "1AA10003", "Chinese", "xiangtan"), Row("xiangtan", "1AA100031", "Chinese", "xiangtan"), Row("xiangtan", "1AA100038", "Chinese", "xiangtan"), Row("xiangtan", "1AA100041", "Chinese", "xiangtan"), Row("xiangtan", "1AA100048", "Chinese", "xiangtan"), Row("xiangtan", "1AA100049", "Chinese", "xiangtan"), Row("xiangtan", "1AA100051", "Chinese", "xiangtan"), Row("xiangtan", "1AA100059", "Chinese", "xiangtan"), Row("xiangtan", "1AA100068", "Chinese", "xiangtan"), Row("xiangtan", "1AA10007", "Chinese", "xiangtan"), Row("xiangtan", "1AA100072", "Chinese", "xiangtan"), Row("xiangtan", "1AA100080", "Chinese", "xiangtan"), Row("xiangtan", "1AA100082", "Chinese", "xiangtan"), Row("xiangtan", "1AA100084", "Chinese", "xiangtan"), Row("yichang", "1AA100040", "Chinese", "yichang"), Row("yichang", "1AA100043", "Chinese", "yichang"), Row("yichang", "1AA100050", "Chinese", "yichang"), Row("yichang", "1AA100066", "Chinese", "yichang"), Row("yichang", "1AA100069", "Chinese", "yichang"), Row("yichang", "1AA100076", "Chinese", "yichang"), Row("yichang", "1AA100", "Chinese", "yichang"), Row("yichang", "1AA100000", "Chinese", "yichang"), Row("yichang", "1AA100018", "Chinese", "yichang"), Row("yichang", "1AA10002", "Chinese", "yichang"), Row("yichang", "1AA100023", "Chinese", "yichang"), Row("yichang", "1AA100033", "Chinese", "yichang"), Row("changsha", "1AA100057", "Chinese", "yuhua"), Row("changsha", "1AA100064", "Chinese", "yuhua"), Row("changsha", "1AA100065", "Chinese", "yuhua"), Row("changsha", "1AA100067", "Chinese", "yuhua"), Row("changsha", "1AA100071", "Chinese", "yuhua"), Row("changsha", "1AA100077", "Chinese", "yuhua"), Row("changsha", "1AA100079", "Chinese", "yuhua"), Row("changsha", "1AA10008", "Chinese", "yuhua"), Row("changsha", "1AA1000", "Chinese", "yuhua"), Row("changsha", "1AA100005", "Chinese", "yuhua"), Row("changsha", "1AA100006", "Chinese", "yuhua"), Row("changsha", "1AA100014", "Chinese", "yuhua"), Row("changsha", "1AA100015", "Chinese", "yuhua"), Row("changsha", "1AA100019", "Chinese", "yuhua"), Row("changsha", "1AA100021", "Chinese", "yuhua"), Row("changsha", "1AA100029", "Chinese", "yuhua"), Row("changsha", "1AA100034", "Chinese", "yuhua"), Row("changsha", "1AA100036", "Chinese", "yuhua"), Row("changsha", "1AA100037", "Chinese", "yuhua")))
  })

  //TC_281
  test("SELECT Activecity, ActiveCountry, ActiveDistrict, MIN(imei) AS Min_imei, MAX(imei) AS Max_imei FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY Activecity, ActiveCountry, ActiveDistrict ORDER BY Activecity ASC, ActiveCountry ASC, ActiveDistrict ASC")({
    checkAnswer(
      sql("SELECT Activecity, ActiveCountry, ActiveDistrict, MIN(imei) AS Min_imei, MAX(imei) AS Max_imei FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY Activecity, ActiveCountry, ActiveDistrict ORDER BY Activecity ASC, ActiveCountry ASC, ActiveDistrict ASC"),
      Seq(Row("changsha", "Chinese", "yuhua", "1AA1000", "1AA10008"), Row("guangzhou", "Chinese", "longhua", "1AA1", "1AA100075"), Row("shenzhen", "Chinese", "longgang", "1AA100013", "1AA100083"), Row("wuhan", "Chinese", "hongshan", "1AA10", "1AA100078"), Row("xiangtan", "Chinese", "xiangtan", "1AA10000", "1AA100084"), Row("yichang", "Chinese", "yichang", "1AA100", "1AA100076"), Row("zhuzhou", "Chinese", "tianyuan", "1AA100002", "1AA100081")))
  })


  //TC_322
  test("SELECT ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series = \"5Series\" GROUP BY ActiveCountry, ActiveDistrict, deliveryCity ORDER BY ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series = \"5Series\" GROUP BY ActiveCountry, ActiveDistrict, deliveryCity ORDER BY ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC"),
      Seq(Row("Chinese", "hongshan", "guangzhou", 2015), Row("Chinese", "longgang", "changsha", 2015), Row("Chinese", "longgang", "guangzhou", 2015), Row("Chinese", "longgang", "shenzhen", 2015), Row("Chinese", "longgang", "zhuzhou", 2015), Row("Chinese", "longhua", "zhuzhou", 2015), Row("Chinese", "tianyuan", "shenzhen", 2015), Row("Chinese", "tianyuan", "zhuzhou", 2015), Row("Chinese", "xiangtan", "shenzhen", 2015), Row("Chinese", "xiangtan", "xiangtan", 2015), Row("Chinese", "yichang", "guangzhou", 2015), Row("Chinese", "yichang", "xiangtan", 2015), Row("Chinese", "yuhua", "changsha", 2015), Row("Chinese", "yuhua", "shenzhen", 2015), Row("Chinese", "yuhua", "wuhan", 2015), Row("Chinese", "yuhua", "zhuzhou", 4030)))
  })

  //TC_323
  test("SELECT ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE deliveryCity = \"zhuzhou\" GROUP BY ActiveCountry, ActiveDistrict, deliveryCity ORDER BY ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE deliveryCity = \"zhuzhou\" GROUP BY ActiveCountry, ActiveDistrict, deliveryCity ORDER BY ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC"),
      Seq(Row("Chinese", "hongshan", "zhuzhou", 2015), Row("Chinese", "longgang", "zhuzhou", 6045), Row("Chinese", "longhua", "zhuzhou", 6045), Row("Chinese", "tianyuan", "zhuzhou", 6045), Row("Chinese", "yichang", "zhuzhou", 2015), Row("Chinese", "yuhua", "zhuzhou", 8060)))
  })

  //TC_324
  test("SELECT modelId, ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE modelId > \"2000\" GROUP BY modelId, ActiveCountry, ActiveDistrict, deliveryCity ORDER BY modelId ASC, ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT modelId, ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE modelId > \"2000\" GROUP BY modelId, ActiveCountry, ActiveDistrict, deliveryCity ORDER BY modelId ASC, ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC"),
      Seq(Row("2008", "Chinese", "tianyuan", "changsha", 2015), Row("2069", "Chinese", "yuhua", "changsha", 2015), Row("2074", "Chinese", "longgang", "wuhan", 2015), Row("2133", "Chinese", "xiangtan", "shenzhen", 2015), Row("2142", "Chinese", "tianyuan", "yichang", 2015), Row("2151", "Chinese", "yuhua", "zhuzhou", 2015), Row("2164", "Chinese", "yuhua", "xiangtan", 2015), Row("2167", "Chinese", "longgang", "changsha", 2015), Row("2176", "Chinese", "longgang", "xiangtan", 2015), Row("2201", "Chinese", "yichang", "wuhan", 2015), Row("2300", "Chinese", "tianyuan", "zhuzhou", 2015), Row("2319", "Chinese", "hongshan", "guangzhou", 2015), Row("2320", "Chinese", "xiangtan", "guangzhou", 2015), Row("2355", "Chinese", "xiangtan", "shenzhen", 2015), Row("2381", "Chinese", "yuhua", "xiangtan", 2015), Row("2408", "Chinese", "xiangtan", "guangzhou", 2015), Row("2415", "Chinese", "yuhua", "changsha", 2015), Row("2457", "Chinese", "yichang", "yichang", 2015), Row("2479", "Chinese", "xiangtan", "yichang", 2015), Row("2531", "Chinese", "yuhua", "wuhan", 2015), Row("2563", "Chinese", "hongshan", "changsha", 2015), Row("2574", "Chinese", "longgang", "shenzhen", 2015), Row("2591", "Chinese", "yichang", "xiangtan", 2015), Row("2594", "Chinese", "tianyuan", "yichang", 2015), Row("2597", "Chinese", "longgang", "guangzhou", 2015), Row("2644", "Chinese", "xiangtan", "xiangtan", 2015), Row("2696", "Chinese", "longhua", "zhuzhou", 2015), Row("2705", "Chinese", "xiangtan", "guangzhou", 2015), Row("273", "Chinese", "yichang", "wuhan", 2015), Row("2759", "Chinese", "yuhua", "yichang", 2015), Row("2761", "Chinese", "xiangtan", "xiangtan", 2015), Row("2765", "Chinese", "hongshan", "yichang", 2015), Row("2797", "Chinese", "xiangtan", "xiangtan", 2015), Row("2799", "Chinese", "yuhua", "yichang", 2015), Row("2823", "Chinese", "longhua", "yichang", 2015), Row("2828", "Chinese", "yichang", "zhuzhou", 2015), Row("2930", "Chinese", "longhua", "yichang", 2015), Row("2940", "Chinese", "tianyuan", "zhuzhou", 2015), Row("2963", "Chinese", "xiangtan", "changsha", 2015), Row("297", "Chinese", "longgang", "zhuzhou", 2015), Row("396", "Chinese", "xiangtan", "changsha", 2015), Row("44", "Chinese", "hongshan", "guangzhou", 2015), Row("446", "Chinese", "yichang", "yichang", 2015), Row("466", "Chinese", "xiangtan", "guangzhou", 2015), Row("47", "Chinese", "hongshan", "guangzhou", 2015), Row("477", "Chinese", "xiangtan", "yichang", 2015), Row("499", "Chinese", "xiangtan", "shenzhen", 2015), Row("513", "Chinese", "longhua", "zhuzhou", 2015), Row("546", "Chinese", "xiangtan", "changsha", 2015), Row("631", "Chinese", "tianyuan", "shenzhen", 2015), Row("68", "Chinese", "hongshan", "wuhan", 2015), Row("716", "Chinese", "yuhua", "zhuzhou", 2015), Row("776", "Chinese", "longgang", "zhuzhou", 2015), Row("839", "Chinese", "hongshan", "zhuzhou", 2015), Row("864", "Chinese", "hongshan", "guangzhou", 2015), Row("872", "Chinese", "xiangtan", "shenzhen", 2015), Row("93", "Chinese", "hongshan", "yichang", 2015), Row("987", "Chinese", "yichang", "wuhan", 2015)))
  })

  //TC_325
  test("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE modelId > \"2000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE modelId > \"2000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      Seq(Row("1AA10", "yichang", 2015, 1714.635), Row("1AA100", "xiangtan", 2015, 1271.0), Row("1AA1000", "wuhan", 2015, 692.0), Row("1AA10000", "guangzhou", 2015, 2175.0), Row("1AA1000000", "yichang", 2015, 1600.0), Row("1AA100002", "changsha", 2015, 1341.0), Row("1AA100005", "yichang", 2015, 2593.0), Row("1AA100006", "changsha", 2015, 2572.0), Row("1AA100007", "changsha", 2015, 1991.0), Row("1AA100009", "yichang", 2015, 1841.0), Row("1AA10001", "changsha", 2015, 298.0), Row("1AA100010", "zhuzhou", 2015, 79.0), Row("1AA100011", "guangzhou", 2015, 202.0), Row("1AA100012", "xiangtan", 2015, 568.0), Row("1AA100013", "changsha", 2015, 355.0), Row("1AA100016", "changsha", 2015, 1873.0), Row("1AA100018", "yichang", 2015, 441.0), Row("1AA100019", "zhuzhou", 2015, 2194.0), Row("1AA10002", "wuhan", 2015, 2972.0), Row("1AA100020", "shenzhen", 2015, 256.0), Row("1AA100025", "guangzhou", 2015, 1724.0), Row("1AA100026", "yichang", 2015, 1768.0), Row("1AA100027", "zhuzhou", 2015, 2436.0), Row("1AA100028", "zhuzhou", 2015, 2849.0), Row("1AA100030", "zhuzhou", 2015, 1333.0), Row("1AA100033", "wuhan", 2015, 760.0), Row("1AA100036", "changsha", 2015, 2224.0), Row("1AA100037", "xiangtan", 2015, 1015.0), Row("1AA100038", "shenzhen", 2015, 1229.0), Row("1AA10004", "guangzhou", 2015, 1717.0), Row("1AA100041", "shenzhen", 2015, 2734.0), Row("1AA100042", "shenzhen", 2015, 2745.0), Row("1AA100046", "guangzhou", 2015, 1077.0), Row("1AA100047", "zhuzhou", 2015, 1823.0), Row("1AA100049", "guangzhou", 2015, 2890.0), Row("1AA100050", "yichang", 2015, 29.0), Row("1AA100051", "guangzhou", 2015, 1407.0), Row("1AA100052", "zhuzhou", 2015, 845.0), Row("1AA100055", "yichang", 2015, 1728.0), Row("1AA100056", "wuhan", 2015, 750.0), Row("1AA100057", "zhuzhou", 2015, 2288.0), Row("1AA100058", "guangzhou", 2015, 2635.0), Row("1AA100059", "shenzhen", 2015, 1337.0), Row("1AA100060", "xiangtan", 2015, 538.0), Row("1AA100061", "changsha", 2015, 1407.0), Row("1AA100062", "yichang", 2015, 2952.0), Row("1AA100063", "yichang", 2015, 1226.0), Row("1AA100066", "zhuzhou", 2015, 1864.0), Row("1AA10007", "xiangtan", 2015, 1350.0), Row("1AA100070", "guangzhou", 2015, 1567.0), Row("1AA100073", "zhuzhou", 2015, 2488.0), Row("1AA100074", "wuhan", 2015, 907.0), Row("1AA100076", "wuhan", 2015, 732.0), Row("1AA100077", "yichang", 2015, 2077.0), Row("1AA100078", "yichang", 2015, 1434.0), Row("1AA100079", "xiangtan", 2015, 1098.0), Row("1AA100080", "shenzhen", 2015, 954.0), Row("1AA100082", "xiangtan", 2015, 2348.0)))
  })

  //TC_326
  test("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE imei >= \"1AA1000000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE imei >= \"1AA1000000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      Seq(Row("1AA1000000", "yichang", 2015, 1600.0), Row("1AA100001", "xiangtan", 2015, 505.0), Row("1AA100002", "changsha", 2015, 1341.0), Row("1AA100003", "zhuzhou", 2015, 2239.0), Row("1AA100004", "yichang", 2015, 2970.0), Row("1AA100005", "yichang", 2015, 2593.0), Row("1AA100006", "changsha", 2015, 2572.0), Row("1AA100007", "changsha", 2015, 1991.0), Row("1AA100008", "changsha", 2015, 1442.0), Row("1AA100009", "yichang", 2015, 1841.0), Row("1AA10001", "changsha", 2015, 298.0), Row("1AA100010", "zhuzhou", 2015, 79.0), Row("1AA100011", "guangzhou", 2015, 202.0), Row("1AA100012", "xiangtan", 2015, 568.0), Row("1AA100013", "changsha", 2015, 355.0), Row("1AA100014", "zhuzhou", 2015, 151.0), Row("1AA100015", "xiangtan", 2015, 2863.0), Row("1AA100016", "changsha", 2015, 1873.0), Row("1AA100017", "xiangtan", 2015, 2205.0), Row("1AA100018", "yichang", 2015, 441.0), Row("1AA100019", "zhuzhou", 2015, 2194.0), Row("1AA10002", "wuhan", 2015, 2972.0), Row("1AA100020", "shenzhen", 2015, 256.0), Row("1AA100021", "changsha", 2015, 1778.0), Row("1AA100022", "zhuzhou", 2015, 1999.0), Row("1AA100023", "guangzhou", 2015, 2194.0), Row("1AA100024", "changsha", 2015, 2483.0), Row("1AA100025", "guangzhou", 2015, 1724.0), Row("1AA100026", "yichang", 2015, 1768.0), Row("1AA100027", "zhuzhou", 2015, 2436.0), Row("1AA100028", "zhuzhou", 2015, 2849.0), Row("1AA100029", "xiangtan", 2015, 1691.0), Row("1AA10003", "xiangtan", 2015, 2071.0), Row("1AA100030", "zhuzhou", 2015, 1333.0), Row("1AA100031", "yichang", 2015, 1080.0), Row("1AA100032", "shenzhen", 2015, 1053.0), Row("1AA100033", "wuhan", 2015, 760.0), Row("1AA100034", "guangzhou", 2015, 2061.0), Row("1AA100035", "changsha", 2015, 2142.0), Row("1AA100036", "changsha", 2015, 2224.0), Row("1AA100037", "xiangtan", 2015, 1015.0), Row("1AA100038", "shenzhen", 2015, 1229.0), Row("1AA100039", "shenzhen", 2015, 1750.0), Row("1AA10004", "guangzhou", 2015, 1717.0), Row("1AA100040", "yichang", 2015, 2078.0), Row("1AA100041", "shenzhen", 2015, 2734.0), Row("1AA100042", "shenzhen", 2015, 2745.0), Row("1AA100043", "guangzhou", 2015, 571.0), Row("1AA100044", "guangzhou", 2015, 1697.0), Row("1AA100045", "xiangtan", 2015, 2553.0), Row("1AA100046", "guangzhou", 2015, 1077.0), Row("1AA100047", "zhuzhou", 2015, 1823.0), Row("1AA100048", "guangzhou", 2015, 2399.0), Row("1AA100049", "guangzhou", 2015, 2890.0), Row("1AA10005", "xiangtan", 2015, 1608.0), Row("1AA100050", "yichang", 2015, 29.0), Row("1AA100051", "guangzhou", 2015, 1407.0), Row("1AA100052", "zhuzhou", 2015, 845.0), Row("1AA100053", "wuhan", 2015, 1655.0), Row("1AA100054", "shenzhen", 2015, 1368.0), Row("1AA100055", "yichang", 2015, 1728.0), Row("1AA100056", "wuhan", 2015, 750.0), Row("1AA100057", "zhuzhou", 2015, 2288.0), Row("1AA100058", "guangzhou", 2015, 2635.0), Row("1AA100059", "shenzhen", 2015, 1337.0), Row("1AA10006", "guangzhou", 2015, 2478.0), Row("1AA100060", "xiangtan", 2015, 538.0), Row("1AA100061", "changsha", 2015, 1407.0), Row("1AA100062", "yichang", 2015, 2952.0), Row("1AA100063", "yichang", 2015, 1226.0), Row("1AA100064", "zhuzhou", 2015, 865.0), Row("1AA100065", "xiangtan", 2015, 901.0), Row("1AA100066", "zhuzhou", 2015, 1864.0), Row("1AA100067", "wuhan", 2015, 572.0), Row("1AA100068", "guangzhou", 2015, 412.0), Row("1AA100069", "xiangtan", 2015, 1491.0), Row("1AA10007", "xiangtan", 2015, 1350.0), Row("1AA100070", "guangzhou", 2015, 1567.0), Row("1AA100071", "guangzhou", 2015, 1973.0), Row("1AA100072", "changsha", 2015, 448.0), Row("1AA100073", "zhuzhou", 2015, 2488.0), Row("1AA100074", "wuhan", 2015, 907.0), Row("1AA100075", "shenzhen", 2015, 2507.0), Row("1AA100076", "wuhan", 2015, 732.0), Row("1AA100077", "yichang", 2015, 2077.0), Row("1AA100078", "yichang", 2015, 1434.0), Row("1AA100079", "xiangtan", 2015, 1098.0), Row("1AA10008", "shenzhen", 2015, 813.0), Row("1AA100080", "shenzhen", 2015, 954.0), Row("1AA100081", "shenzhen", 2015, 613.0), Row("1AA100082", "xiangtan", 2015, 2348.0), Row("1AA100083", "zhuzhou", 2015, 2192.0), Row("1AA100084", "guangzhou", 2015, 2826.0)))
  })

  //TC_328
  test("SELECT imei, deliveryCity, series, Latest_YEAR, gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE imei >= \"1AA1000000\" ORDER BY series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, Latest_YEAR, gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE imei >= \"1AA1000000\" ORDER BY series ASC"),
      Seq(Row("1AA100049", "guangzhou", "0Series", 2015, 2890.0), Row("1AA100065", "xiangtan", "0Series", 2015, 901.0), Row("1AA100070", "guangzhou", "0Series", 2015, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 1973.0), Row("1AA100076", "wuhan", "0Series", 2015, 732.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 2826.0), Row("1AA100001", "xiangtan", "0Series", 2015, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 1341.0), Row("1AA100009", "yichang", "0Series", 2015, 1841.0), Row("1AA100011", "guangzhou", "0Series", 2015, 202.0), Row("1AA10002", "wuhan", "0Series", 2015, 2972.0), Row("1AA100021", "changsha", "0Series", 2015, 1778.0), Row("1AA100025", "guangzhou", "0Series", 2015, 1724.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 2436.0), Row("1AA100005", "yichang", "1Series", 2015, 2593.0), Row("1AA100013", "changsha", "1Series", 2015, 355.0), Row("1AA100032", "shenzhen", "1Series", 2015, 1053.0), Row("1AA100045", "xiangtan", "2Series", 2015, 2553.0), Row("1AA100050", "yichang", "2Series", 2015, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 1407.0), Row("1AA100053", "wuhan", "2Series", 2015, 1655.0), Row("1AA100063", "yichang", "2Series", 2015, 1226.0), Row("1AA100078", "yichang", "2Series", 2015, 1434.0), Row("1AA10001", "changsha", "2Series", 2015, 298.0), Row("1AA100029", "xiangtan", "2Series", 2015, 1691.0), Row("1AA100034", "guangzhou", "2Series", 2015, 2061.0), Row("1AA100042", "shenzhen", "3Series", 2015, 2745.0), Row("1AA100046", "guangzhou", "3Series", 2015, 1077.0), Row("1AA100048", "guangzhou", "3Series", 2015, 2399.0), Row("1AA10006", "guangzhou", "3Series", 2015, 2478.0), Row("1AA100075", "shenzhen", "3Series", 2015, 2507.0), Row("1AA100077", "yichang", "3Series", 2015, 2077.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 79.0), Row("1AA100016", "changsha", "3Series", 2015, 1873.0), Row("1AA100004", "yichang", "4Series", 2015, 2970.0), Row("1AA100012", "xiangtan", "4Series", 2015, 568.0), Row("1AA100015", "xiangtan", "4Series", 2015, 2863.0), Row("1AA100059", "shenzhen", "4Series", 2015, 1337.0), Row("1AA100067", "wuhan", "4Series", 2015, 572.0), Row("1AA100072", "changsha", "4Series", 2015, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 2488.0), Row("1AA100079", "xiangtan", "4Series", 2015, 1098.0), Row("1AA100041", "shenzhen", "5Series", 2015, 2734.0), Row("1AA100058", "guangzhou", "5Series", 2015, 2635.0), Row("1AA10008", "shenzhen", "5Series", 2015, 813.0), Row("1AA100081", "shenzhen", "5Series", 2015, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 2348.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 2239.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 151.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 2194.0), Row("1AA100020", "shenzhen", "5Series", 2015, 256.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 2194.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 2849.0), Row("1AA100035", "changsha", "5Series", 2015, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 2224.0), Row("1AA10004", "guangzhou", "5Series", 2015, 1717.0), Row("1AA100006", "changsha", "6Series", 2015, 2572.0), Row("1AA100024", "changsha", "6Series", 2015, 2483.0), Row("1AA100038", "shenzhen", "6Series", 2015, 1229.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 845.0), Row("1AA100056", "wuhan", "6Series", 2015, 750.0), Row("1AA100061", "changsha", "6Series", 2015, 1407.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 865.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 1864.0), Row("1AA100074", "wuhan", "6Series", 2015, 907.0), Row("1AA1000000", "yichang", "7Series", 2015, 1600.0), Row("1AA100026", "yichang", "7Series", 2015, 1768.0), Row("1AA10003", "xiangtan", "7Series", 2015, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 1080.0), Row("1AA100037", "xiangtan", "7Series", 2015, 1015.0), Row("1AA100054", "shenzhen", "7Series", 2015, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 1728.0), Row("1AA100008", "changsha", "8Series", 2015, 1442.0), Row("1AA100018", "yichang", "8Series", 2015, 441.0), Row("1AA100033", "wuhan", "8Series", 2015, 760.0), Row("1AA100039", "shenzhen", "8Series", 2015, 1750.0), Row("1AA100040", "yichang", "8Series", 2015, 2078.0), Row("1AA100044", "guangzhou", "8Series", 2015, 1697.0), Row("1AA10005", "xiangtan", "8Series", 2015, 1608.0), Row("1AA100060", "xiangtan", "8Series", 2015, 538.0), Row("1AA100068", "guangzhou", "8Series", 2015, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 1350.0), Row("1AA100007", "changsha", "9Series", 2015, 1991.0), Row("1AA100017", "xiangtan", "9Series", 2015, 2205.0), Row("1AA100043", "guangzhou", "9Series", 2015, 571.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 1823.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 2288.0), Row("1AA100062", "yichang", "9Series", 2015, 2952.0), Row("1AA100080", "shenzhen", "9Series", 2015, 954.0)))
  })

  //TC_330
  test("SELECT deliveryCity, channelsId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, channelsId ORDER BY deliveryCity ASC, channelsId ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, channelsId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, channelsId ORDER BY deliveryCity ASC, channelsId ASC"),
      Seq(Row("changsha", "1", 100008), Row("changsha", "2", 200030), Row("changsha", "3", 300025), Row("changsha", "5", 110037), Row("changsha", "6", 300095), Row("changsha", "7", 200107), Row("guangzhou", "1", 310084), Row("guangzhou", "2", 100044), Row("guangzhou", "3", 200093), Row("guangzhou", "4", 310192), Row("guangzhou", "5", 110077), Row("guangzhou", "6", 300126), Row("guangzhou", "7", 200119), Row("shenzhen", "1", 200080), Row("shenzhen", "2", 200135), Row("shenzhen", "3", 100038), Row("shenzhen", "4", 10008), Row("shenzhen", "5", 100042), Row("shenzhen", "6", 200155), Row("shenzhen", "7", 300111), Row("wuhan", "3", 101053), Row("wuhan", "5", 110058), Row("wuhan", "6", 200033), Row("wuhan", "7", 300217), Row("xiangtan", "1", 100015), Row("xiangtan", "2", 110017), Row("xiangtan", "3", 110024), Row("xiangtan", "4", 110063), Row("xiangtan", "5", 200098), Row("xiangtan", "6", 300261), Row("xiangtan", "7", 300148), Row("yichang", "1", 300086), Row("yichang", "2", 100004), Row("yichang", "3", 200072), Row("yichang", "4", 100029), Row("yichang", "5", 100078), Row("yichang", "6", 1200139), Row("yichang", "7", 300121), Row("zhuzhou", "1", 100047), Row("zhuzhou", "2", 200156), Row("zhuzhou", "3", 400066), Row("zhuzhou", "4", 200093), Row("zhuzhou", "5", 200050), Row("zhuzhou", "6", 200067), Row("zhuzhou", "7", 200116)))
  })

  //TC_331
  test("SELECT series, imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId, SUM(gamepointid) AS Sum_gamepointid FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId, SUM(gamepointid) AS Sum_gamepointid FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      Seq(Row("0Series", "1AA100001", "xiangtan", 1, 7, 2015, 2919786.0, 100001, 505.0, 505.0), Row("0Series", "1AA100002", "changsha", 1, 7, 2015, 9455612.0, 100002, 1341.0, 1341.0), Row("0Series", "1AA100009", "yichang", 1, 7, 2015, 2389657.0, 100009, 1841.0, 1841.0), Row("0Series", "1AA100011", "guangzhou", 1, 7, 2015, 4816260.0, 100011, 202.0, 202.0), Row("0Series", "1AA10002", "wuhan", 1, 7, 2015, 5204739.0, 10002, 2972.0, 2972.0), Row("0Series", "1AA100021", "changsha", 1, 7, 2015, 566917.0, 100021, 1778.0, 1778.0), Row("0Series", "1AA100025", "guangzhou", 1, 7, 2015, 6533899.0, 100025, 1724.0, 1724.0), Row("0Series", "1AA100027", "zhuzhou", 1, 7, 2015, 4750239.0, 100027, 2436.0, 2436.0), Row("0Series", "1AA100049", "guangzhou", 1, 7, 2015, 1952050.0, 100049, 2890.0, 2890.0), Row("0Series", "1AA100065", "xiangtan", 1, 7, 2015, 3166724.0, 100065, 901.0, 901.0), Row("0Series", "1AA100070", "guangzhou", 1, 7, 2015, 4202614.0, 100070, 1567.0, 1567.0), Row("0Series", "1AA100071", "guangzhou", 1, 7, 2015, 2199957.0, 100071, 1973.0, 1973.0), Row("0Series", "1AA100076", "wuhan", 1, 7, 2015, 8069859.0, 100076, 732.0, 732.0), Row("0Series", "1AA100083", "zhuzhou", 1, 7, 2015, 507229.0, 100083, 2192.0, 2192.0), Row("0Series", "1AA100084", "guangzhou", 1, 7, 2015, 8976568.0, 100084, 2826.0, 2826.0), Row("1Series", "1AA100005", "yichang", 1, 7, 2015, 3940720.0, 100005, 2593.0, 2593.0), Row("1Series", "1AA100013", "changsha", 1, 7, 2015, 2051539.0, 100013, 355.0, 355.0), Row("1Series", "1AA100032", "shenzhen", 1, 7, 2015, 6994063.0, 100032, 1053.0, 1053.0), Row("2Series", "1AA10001", "changsha", 1, 7, 2015, 5986189.0, 10001, 298.0, 298.0), Row("2Series", "1AA100029", "xiangtan", 1, 7, 2015, 7774590.0, 100029, 1691.0, 1691.0), Row("2Series", "1AA100034", "guangzhou", 1, 7, 2015, 5797079.0, 100034, 2061.0, 2061.0), Row("2Series", "1AA100045", "xiangtan", 1, 7, 2015, 9952232.0, 100045, 2553.0, 2553.0), Row("2Series", "1AA100050", "yichang", 1, 7, 2015, 7768468.0, 100050, 29.0, 29.0), Row("2Series", "1AA100051", "guangzhou", 1, 7, 2015, 7236919.0, 100051, 1407.0, 1407.0), Row("2Series", "1AA100053", "wuhan", 1, 7, 2015, 2651084.0, 100053, 1655.0, 1655.0), Row("2Series", "1AA100063", "yichang", 1, 7, 2015, 9318234.0, 100063, 1226.0, 1226.0), Row("2Series", "1AA100078", "yichang", 1, 7, 2015, 6428516.0, 100078, 1434.0, 1434.0), Row("3Series", "1AA100010", "zhuzhou", 1, 7, 2015, 8543280.0, 100010, 79.0, 79.0), Row("3Series", "1AA100016", "changsha", 1, 7, 2015, 6495292.0, 100016, 1873.0, 1873.0), Row("3Series", "1AA100042", "shenzhen", 1, 7, 2015, 23250.0, 100042, 2745.0, 2745.0), Row("3Series", "1AA100046", "guangzhou", 1, 7, 2015, 424923.0, 100046, 1077.0, 1077.0), Row("3Series", "1AA100048", "guangzhou", 1, 7, 2015, 9500486.0, 100048, 2399.0, 2399.0), Row("3Series", "1AA10006", "guangzhou", 1, 7, 2015, 9394732.0, 10006, 2478.0, 2478.0), Row("3Series", "1AA100075", "shenzhen", 1, 7, 2015, 3215327.0, 100075, 2507.0, 2507.0), Row("3Series", "1AA100077", "yichang", 1, 7, 2015, 6383562.0, 100077, 2077.0, 2077.0), Row("4Series", "1AA100004", "yichang", 1, 7, 2015, 1439363.0, 100004, 2970.0, 2970.0), Row("4Series", "1AA100012", "xiangtan", 1, 7, 2015, 8453995.0, 100012, 568.0, 568.0), Row("4Series", "1AA100015", "xiangtan", 1, 7, 2015, 6663091.0, 100015, 2863.0, 2863.0), Row("4Series", "1AA100059", "shenzhen", 1, 7, 2015, 168757.0, 100059, 1337.0, 1337.0), Row("4Series", "1AA100067", "wuhan", 1, 7, 2015, 7575196.0, 100067, 572.0, 572.0), Row("4Series", "1AA100072", "changsha", 1, 7, 2015, 511128.0, 100072, 448.0, 448.0), Row("4Series", "1AA100073", "zhuzhou", 1, 7, 2015, 580612.0, 100073, 2488.0, 2488.0), Row("4Series", "1AA100079", "xiangtan", 1, 7, 2015, 5159121.0, 100079, 1098.0, 1098.0), Row("5Series", "1AA100", "xiangtan", 1, 7, 2015, 8231335.0, 100, 1271.0, 1271.0), Row("5Series", "1AA1000", "wuhan", 1, 7, 2015, 8978765.0, 1000, 692.0, 692.0), Row("5Series", "1AA100003", "zhuzhou", 1, 7, 2015, 88231.0, 100003, 2239.0, 2239.0), Row("5Series", "1AA100014", "zhuzhou", 1, 7, 2015, 7610075.0, 100014, 151.0, 151.0), Row("5Series", "1AA100019", "zhuzhou", 1, 7, 2015, 4459076.0, 100019, 2194.0, 2194.0), Row("5Series", "1AA100020", "shenzhen", 1, 7, 2015, 833654.0, 100020, 256.0, 256.0), Row("5Series", "1AA100022", "zhuzhou", 1, 7, 2015, 832387.0, 100022, 1999.0, 1999.0), Row("5Series", "1AA100023", "guangzhou", 1, 7, 2015, 2850246.0, 100023, 2194.0, 2194.0), Row("5Series", "1AA100028", "zhuzhou", 1, 7, 2015, 8880112.0, 100028, 2849.0, 2849.0), Row("5Series", "1AA100035", "changsha", 1, 7, 2015, 6283062.0, 100035, 2142.0, 2142.0), Row("5Series", "1AA100036", "changsha", 1, 7, 2015, 8431770.0, 100036, 2224.0, 2224.0), Row("5Series", "1AA10004", "guangzhou", 1, 7, 2015, 3077303.0, 10004, 1717.0, 1717.0), Row("5Series", "1AA100041", "shenzhen", 1, 7, 2015, 3454331.0, 100041, 2734.0, 2734.0), Row("5Series", "1AA100058", "guangzhou", 1, 7, 2015, 5403108.0, 100058, 2635.0, 2635.0), Row("5Series", "1AA10008", "shenzhen", 1, 7, 2015, 3360388.0, 10008, 813.0, 813.0), Row("5Series", "1AA100081", "shenzhen", 1, 7, 2015, 3335480.0, 100081, 613.0, 613.0), Row("5Series", "1AA100082", "xiangtan", 1, 7, 2015, 994815.0, 100082, 2348.0, 2348.0), Row("6Series", "1AA100006", "changsha", 1, 7, 2015, 4451217.0, 100006, 2572.0, 2572.0), Row("6Series", "1AA100024", "changsha", 1, 7, 2015, 6169467.0, 100024, 2483.0, 2483.0), Row("6Series", "1AA100038", "shenzhen", 1, 7, 2015, 2843881.0, 100038, 1229.0, 1229.0), Row("6Series", "1AA100052", "zhuzhou", 1, 7, 2015, 167725.0, 100052, 845.0, 845.0), Row("6Series", "1AA100056", "wuhan", 1, 7, 2015, 1753823.0, 100056, 750.0, 750.0), Row("6Series", "1AA100061", "changsha", 1, 7, 2015, 4358621.0, 100061, 1407.0, 1407.0), Row("6Series", "1AA100064", "zhuzhou", 1, 7, 2015, 5565240.0, 100064, 865.0, 865.0), Row("6Series", "1AA100066", "zhuzhou", 1, 7, 2015, 5592457.0, 100066, 1864.0, 1864.0), Row("6Series", "1AA100074", "wuhan", 1, 7, 2015, 275342.0, 100074, 907.0, 907.0), Row("7Series", "1AA1", "yichang", 1, 7, 2015, 5281803.0, 1, 2738.562, 2738.562), Row("7Series", "1AA10", "yichang", 1, 7, 2015, 6805600.0, 10, 1714.635, 1714.635), Row("7Series", "1AA10000", "guangzhou", 1, 7, 2015, 3784858.0, 10000, 2175.0, 2175.0), Row("7Series", "1AA1000000", "yichang", 1, 7, 2015, 9737768.0, 1000000, 1600.0, 1600.0), Row("7Series", "1AA100026", "yichang", 1, 7, 2015, 7487134.0, 100026, 1768.0, 1768.0), Row("7Series", "1AA10003", "xiangtan", 1, 7, 2015, 5586718.0, 10003, 2071.0, 2071.0), Row("7Series", "1AA100030", "zhuzhou", 1, 7, 2015, 5857263.0, 100030, 1333.0, 1333.0), Row("7Series", "1AA100031", "yichang", 1, 7, 2015, 6416074.0, 100031, 1080.0, 1080.0), Row("7Series", "1AA100037", "xiangtan", 1, 7, 2015, 3311312.0, 100037, 1015.0, 1015.0), Row("7Series", "1AA100054", "shenzhen", 1, 7, 2015, 6283156.0, 100054, 1368.0, 1368.0), Row("7Series", "1AA100055", "yichang", 1, 7, 2015, 7342321.0, 100055, 1728.0, 1728.0), Row("8Series", "1AA100008", "changsha", 1, 7, 2015, 1070757.0, 100008, 1442.0, 1442.0), Row("8Series", "1AA100018", "yichang", 1, 7, 2015, 574375.0, 100018, 441.0, 441.0), Row("8Series", "1AA100033", "wuhan", 1, 7, 2015, 8229807.0, 100033, 760.0, 760.0), Row("8Series", "1AA100039", "shenzhen", 1, 7, 2015, 1901889.0, 100039, 1750.0, 1750.0), Row("8Series", "1AA100040", "yichang", 1, 7, 2015, 7880439.0, 100040, 2078.0, 2078.0), Row("8Series", "1AA100044", "guangzhou", 1, 7, 2015, 5659107.0, 100044, 1697.0, 1697.0), Row("8Series", "1AA10005", "xiangtan", 1, 7, 2015, 6190068.0, 10005, 1608.0, 1608.0), Row("8Series", "1AA100060", "xiangtan", 1, 7, 2015, 7420815.0, 100060, 538.0, 538.0), Row("8Series", "1AA100068", "guangzhou", 1, 7, 2015, 3235086.0, 100068, 412.0, 412.0), Row("8Series", "1AA100069", "xiangtan", 1, 7, 2015, 7917206.0, 100069, 1491.0, 1491.0), Row("8Series", "1AA10007", "xiangtan", 1, 7, 2015, 4156339.0, 10007, 1350.0, 1350.0), Row("9Series", "1AA100000", "wuhan", 1, 7, 2015, 1602458.0, 100000, 136.0, 136.0), Row("9Series", "1AA100007", "changsha", 1, 7, 2015, 335583.0, 100007, 1991.0, 1991.0), Row("9Series", "1AA100017", "xiangtan", 1, 7, 2015, 2611464.0, 100017, 2205.0, 2205.0), Row("9Series", "1AA100043", "guangzhou", 1, 7, 2015, 3278167.0, 100043, 571.0, 571.0), Row("9Series", "1AA100047", "zhuzhou", 1, 7, 2015, 7839922.0, 100047, 1823.0, 1823.0), Row("9Series", "1AA100057", "zhuzhou", 1, 7, 2015, 5451533.0, 100057, 2288.0, 2288.0), Row("9Series", "1AA100062", "yichang", 1, 7, 2015, 2362114.0, 100062, 2952.0, 2952.0), Row("9Series", "1AA100080", "shenzhen", 1, 7, 2015, 5152985.0, 100080, 954.0, 954.0)))
  })

  //TC_332
  test("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(series) AS Count_series FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(series) AS Count_series FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      Seq(Row("1AA1", "yichang", 1, 7, 5281803.0, 1, 2738.562, 2738.562, 1), Row("1AA10", "yichang", 1, 7, 6805600.0, 10, 1714.635, 1714.635, 1), Row("1AA100", "xiangtan", 1, 7, 8231335.0, 100, 1271.0, 1271.0, 1), Row("1AA1000", "wuhan", 1, 7, 8978765.0, 1000, 692.0, 692.0, 1), Row("1AA10000", "guangzhou", 1, 7, 3784858.0, 10000, 2175.0, 2175.0, 1), Row("1AA100000", "wuhan", 1, 7, 1602458.0, 100000, 136.0, 136.0, 1), Row("1AA1000000", "yichang", 1, 7, 9737768.0, 1000000, 1600.0, 1600.0, 1), Row("1AA100001", "xiangtan", 1, 7, 2919786.0, 100001, 505.0, 505.0, 1), Row("1AA100002", "changsha", 1, 7, 9455612.0, 100002, 1341.0, 1341.0, 1), Row("1AA100003", "zhuzhou", 1, 7, 88231.0, 100003, 2239.0, 2239.0, 1), Row("1AA100004", "yichang", 1, 7, 1439363.0, 100004, 2970.0, 2970.0, 1), Row("1AA100005", "yichang", 1, 7, 3940720.0, 100005, 2593.0, 2593.0, 1), Row("1AA100006", "changsha", 1, 7, 4451217.0, 100006, 2572.0, 2572.0, 1), Row("1AA100007", "changsha", 1, 7, 335583.0, 100007, 1991.0, 1991.0, 1), Row("1AA100008", "changsha", 1, 7, 1070757.0, 100008, 1442.0, 1442.0, 1), Row("1AA100009", "yichang", 1, 7, 2389657.0, 100009, 1841.0, 1841.0, 1), Row("1AA10001", "changsha", 1, 7, 5986189.0, 10001, 298.0, 298.0, 1), Row("1AA100010", "zhuzhou", 1, 7, 8543280.0, 100010, 79.0, 79.0, 1), Row("1AA100011", "guangzhou", 1, 7, 4816260.0, 100011, 202.0, 202.0, 1), Row("1AA100012", "xiangtan", 1, 7, 8453995.0, 100012, 568.0, 568.0, 1), Row("1AA100013", "changsha", 1, 7, 2051539.0, 100013, 355.0, 355.0, 1), Row("1AA100014", "zhuzhou", 1, 7, 7610075.0, 100014, 151.0, 151.0, 1), Row("1AA100015", "xiangtan", 1, 7, 6663091.0, 100015, 2863.0, 2863.0, 1), Row("1AA100016", "changsha", 1, 7, 6495292.0, 100016, 1873.0, 1873.0, 1), Row("1AA100017", "xiangtan", 1, 7, 2611464.0, 100017, 2205.0, 2205.0, 1), Row("1AA100018", "yichang", 1, 7, 574375.0, 100018, 441.0, 441.0, 1), Row("1AA100019", "zhuzhou", 1, 7, 4459076.0, 100019, 2194.0, 2194.0, 1), Row("1AA10002", "wuhan", 1, 7, 5204739.0, 10002, 2972.0, 2972.0, 1), Row("1AA100020", "shenzhen", 1, 7, 833654.0, 100020, 256.0, 256.0, 1), Row("1AA100021", "changsha", 1, 7, 566917.0, 100021, 1778.0, 1778.0, 1), Row("1AA100022", "zhuzhou", 1, 7, 832387.0, 100022, 1999.0, 1999.0, 1), Row("1AA100023", "guangzhou", 1, 7, 2850246.0, 100023, 2194.0, 2194.0, 1), Row("1AA100024", "changsha", 1, 7, 6169467.0, 100024, 2483.0, 2483.0, 1), Row("1AA100025", "guangzhou", 1, 7, 6533899.0, 100025, 1724.0, 1724.0, 1), Row("1AA100026", "yichang", 1, 7, 7487134.0, 100026, 1768.0, 1768.0, 1), Row("1AA100027", "zhuzhou", 1, 7, 4750239.0, 100027, 2436.0, 2436.0, 1), Row("1AA100028", "zhuzhou", 1, 7, 8880112.0, 100028, 2849.0, 2849.0, 1), Row("1AA100029", "xiangtan", 1, 7, 7774590.0, 100029, 1691.0, 1691.0, 1), Row("1AA10003", "xiangtan", 1, 7, 5586718.0, 10003, 2071.0, 2071.0, 1), Row("1AA100030", "zhuzhou", 1, 7, 5857263.0, 100030, 1333.0, 1333.0, 1), Row("1AA100031", "yichang", 1, 7, 6416074.0, 100031, 1080.0, 1080.0, 1), Row("1AA100032", "shenzhen", 1, 7, 6994063.0, 100032, 1053.0, 1053.0, 1), Row("1AA100033", "wuhan", 1, 7, 8229807.0, 100033, 760.0, 760.0, 1), Row("1AA100034", "guangzhou", 1, 7, 5797079.0, 100034, 2061.0, 2061.0, 1), Row("1AA100035", "changsha", 1, 7, 6283062.0, 100035, 2142.0, 2142.0, 1), Row("1AA100036", "changsha", 1, 7, 8431770.0, 100036, 2224.0, 2224.0, 1), Row("1AA100037", "xiangtan", 1, 7, 3311312.0, 100037, 1015.0, 1015.0, 1), Row("1AA100038", "shenzhen", 1, 7, 2843881.0, 100038, 1229.0, 1229.0, 1), Row("1AA100039", "shenzhen", 1, 7, 1901889.0, 100039, 1750.0, 1750.0, 1), Row("1AA10004", "guangzhou", 1, 7, 3077303.0, 10004, 1717.0, 1717.0, 1), Row("1AA100040", "yichang", 1, 7, 7880439.0, 100040, 2078.0, 2078.0, 1), Row("1AA100041", "shenzhen", 1, 7, 3454331.0, 100041, 2734.0, 2734.0, 1), Row("1AA100042", "shenzhen", 1, 7, 23250.0, 100042, 2745.0, 2745.0, 1), Row("1AA100043", "guangzhou", 1, 7, 3278167.0, 100043, 571.0, 571.0, 1), Row("1AA100044", "guangzhou", 1, 7, 5659107.0, 100044, 1697.0, 1697.0, 1), Row("1AA100045", "xiangtan", 1, 7, 9952232.0, 100045, 2553.0, 2553.0, 1), Row("1AA100046", "guangzhou", 1, 7, 424923.0, 100046, 1077.0, 1077.0, 1), Row("1AA100047", "zhuzhou", 1, 7, 7839922.0, 100047, 1823.0, 1823.0, 1), Row("1AA100048", "guangzhou", 1, 7, 9500486.0, 100048, 2399.0, 2399.0, 1), Row("1AA100049", "guangzhou", 1, 7, 1952050.0, 100049, 2890.0, 2890.0, 1), Row("1AA10005", "xiangtan", 1, 7, 6190068.0, 10005, 1608.0, 1608.0, 1), Row("1AA100050", "yichang", 1, 7, 7768468.0, 100050, 29.0, 29.0, 1), Row("1AA100051", "guangzhou", 1, 7, 7236919.0, 100051, 1407.0, 1407.0, 1), Row("1AA100052", "zhuzhou", 1, 7, 167725.0, 100052, 845.0, 845.0, 1), Row("1AA100053", "wuhan", 1, 7, 2651084.0, 100053, 1655.0, 1655.0, 1), Row("1AA100054", "shenzhen", 1, 7, 6283156.0, 100054, 1368.0, 1368.0, 1), Row("1AA100055", "yichang", 1, 7, 7342321.0, 100055, 1728.0, 1728.0, 1), Row("1AA100056", "wuhan", 1, 7, 1753823.0, 100056, 750.0, 750.0, 1), Row("1AA100057", "zhuzhou", 1, 7, 5451533.0, 100057, 2288.0, 2288.0, 1), Row("1AA100058", "guangzhou", 1, 7, 5403108.0, 100058, 2635.0, 2635.0, 1), Row("1AA100059", "shenzhen", 1, 7, 168757.0, 100059, 1337.0, 1337.0, 1), Row("1AA10006", "guangzhou", 1, 7, 9394732.0, 10006, 2478.0, 2478.0, 1), Row("1AA100060", "xiangtan", 1, 7, 7420815.0, 100060, 538.0, 538.0, 1), Row("1AA100061", "changsha", 1, 7, 4358621.0, 100061, 1407.0, 1407.0, 1), Row("1AA100062", "yichang", 1, 7, 2362114.0, 100062, 2952.0, 2952.0, 1), Row("1AA100063", "yichang", 1, 7, 9318234.0, 100063, 1226.0, 1226.0, 1), Row("1AA100064", "zhuzhou", 1, 7, 5565240.0, 100064, 865.0, 865.0, 1), Row("1AA100065", "xiangtan", 1, 7, 3166724.0, 100065, 901.0, 901.0, 1), Row("1AA100066", "zhuzhou", 1, 7, 5592457.0, 100066, 1864.0, 1864.0, 1), Row("1AA100067", "wuhan", 1, 7, 7575196.0, 100067, 572.0, 572.0, 1), Row("1AA100068", "guangzhou", 1, 7, 3235086.0, 100068, 412.0, 412.0, 1), Row("1AA100069", "xiangtan", 1, 7, 7917206.0, 100069, 1491.0, 1491.0, 1), Row("1AA10007", "xiangtan", 1, 7, 4156339.0, 10007, 1350.0, 1350.0, 1), Row("1AA100070", "guangzhou", 1, 7, 4202614.0, 100070, 1567.0, 1567.0, 1), Row("1AA100071", "guangzhou", 1, 7, 2199957.0, 100071, 1973.0, 1973.0, 1), Row("1AA100072", "changsha", 1, 7, 511128.0, 100072, 448.0, 448.0, 1), Row("1AA100073", "zhuzhou", 1, 7, 580612.0, 100073, 2488.0, 2488.0, 1), Row("1AA100074", "wuhan", 1, 7, 275342.0, 100074, 907.0, 907.0, 1), Row("1AA100075", "shenzhen", 1, 7, 3215327.0, 100075, 2507.0, 2507.0, 1), Row("1AA100076", "wuhan", 1, 7, 8069859.0, 100076, 732.0, 732.0, 1), Row("1AA100077", "yichang", 1, 7, 6383562.0, 100077, 2077.0, 2077.0, 1), Row("1AA100078", "yichang", 1, 7, 6428516.0, 100078, 1434.0, 1434.0, 1), Row("1AA100079", "xiangtan", 1, 7, 5159121.0, 100079, 1098.0, 1098.0, 1), Row("1AA10008", "shenzhen", 1, 7, 3360388.0, 10008, 813.0, 813.0, 1), Row("1AA100080", "shenzhen", 1, 7, 5152985.0, 100080, 954.0, 954.0, 1), Row("1AA100081", "shenzhen", 1, 7, 3335480.0, 100081, 613.0, 613.0, 1), Row("1AA100082", "xiangtan", 1, 7, 994815.0, 100082, 2348.0, 2348.0, 1), Row("1AA100083", "zhuzhou", 1, 7, 507229.0, 100083, 2192.0, 2192.0, 1), Row("1AA100084", "guangzhou", 1, 7, 8976568.0, 100084, 2826.0, 2826.0, 1)))
  })

  //TC_333
  test("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(DISTINCT series) AS DistinctCount_series FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(DISTINCT series) AS DistinctCount_series FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      Seq(Row("1AA1", "yichang", 1, 7, 5281803.0, 1, 2738.562, 2738.562, 1), Row("1AA10", "yichang", 1, 7, 6805600.0, 10, 1714.635, 1714.635, 1), Row("1AA100", "xiangtan", 1, 7, 8231335.0, 100, 1271.0, 1271.0, 1), Row("1AA1000", "wuhan", 1, 7, 8978765.0, 1000, 692.0, 692.0, 1), Row("1AA10000", "guangzhou", 1, 7, 3784858.0, 10000, 2175.0, 2175.0, 1), Row("1AA100000", "wuhan", 1, 7, 1602458.0, 100000, 136.0, 136.0, 1), Row("1AA1000000", "yichang", 1, 7, 9737768.0, 1000000, 1600.0, 1600.0, 1), Row("1AA100001", "xiangtan", 1, 7, 2919786.0, 100001, 505.0, 505.0, 1), Row("1AA100002", "changsha", 1, 7, 9455612.0, 100002, 1341.0, 1341.0, 1), Row("1AA100003", "zhuzhou", 1, 7, 88231.0, 100003, 2239.0, 2239.0, 1), Row("1AA100004", "yichang", 1, 7, 1439363.0, 100004, 2970.0, 2970.0, 1), Row("1AA100005", "yichang", 1, 7, 3940720.0, 100005, 2593.0, 2593.0, 1), Row("1AA100006", "changsha", 1, 7, 4451217.0, 100006, 2572.0, 2572.0, 1), Row("1AA100007", "changsha", 1, 7, 335583.0, 100007, 1991.0, 1991.0, 1), Row("1AA100008", "changsha", 1, 7, 1070757.0, 100008, 1442.0, 1442.0, 1), Row("1AA100009", "yichang", 1, 7, 2389657.0, 100009, 1841.0, 1841.0, 1), Row("1AA10001", "changsha", 1, 7, 5986189.0, 10001, 298.0, 298.0, 1), Row("1AA100010", "zhuzhou", 1, 7, 8543280.0, 100010, 79.0, 79.0, 1), Row("1AA100011", "guangzhou", 1, 7, 4816260.0, 100011, 202.0, 202.0, 1), Row("1AA100012", "xiangtan", 1, 7, 8453995.0, 100012, 568.0, 568.0, 1), Row("1AA100013", "changsha", 1, 7, 2051539.0, 100013, 355.0, 355.0, 1), Row("1AA100014", "zhuzhou", 1, 7, 7610075.0, 100014, 151.0, 151.0, 1), Row("1AA100015", "xiangtan", 1, 7, 6663091.0, 100015, 2863.0, 2863.0, 1), Row("1AA100016", "changsha", 1, 7, 6495292.0, 100016, 1873.0, 1873.0, 1), Row("1AA100017", "xiangtan", 1, 7, 2611464.0, 100017, 2205.0, 2205.0, 1), Row("1AA100018", "yichang", 1, 7, 574375.0, 100018, 441.0, 441.0, 1), Row("1AA100019", "zhuzhou", 1, 7, 4459076.0, 100019, 2194.0, 2194.0, 1), Row("1AA10002", "wuhan", 1, 7, 5204739.0, 10002, 2972.0, 2972.0, 1), Row("1AA100020", "shenzhen", 1, 7, 833654.0, 100020, 256.0, 256.0, 1), Row("1AA100021", "changsha", 1, 7, 566917.0, 100021, 1778.0, 1778.0, 1), Row("1AA100022", "zhuzhou", 1, 7, 832387.0, 100022, 1999.0, 1999.0, 1), Row("1AA100023", "guangzhou", 1, 7, 2850246.0, 100023, 2194.0, 2194.0, 1), Row("1AA100024", "changsha", 1, 7, 6169467.0, 100024, 2483.0, 2483.0, 1), Row("1AA100025", "guangzhou", 1, 7, 6533899.0, 100025, 1724.0, 1724.0, 1), Row("1AA100026", "yichang", 1, 7, 7487134.0, 100026, 1768.0, 1768.0, 1), Row("1AA100027", "zhuzhou", 1, 7, 4750239.0, 100027, 2436.0, 2436.0, 1), Row("1AA100028", "zhuzhou", 1, 7, 8880112.0, 100028, 2849.0, 2849.0, 1), Row("1AA100029", "xiangtan", 1, 7, 7774590.0, 100029, 1691.0, 1691.0, 1), Row("1AA10003", "xiangtan", 1, 7, 5586718.0, 10003, 2071.0, 2071.0, 1), Row("1AA100030", "zhuzhou", 1, 7, 5857263.0, 100030, 1333.0, 1333.0, 1), Row("1AA100031", "yichang", 1, 7, 6416074.0, 100031, 1080.0, 1080.0, 1), Row("1AA100032", "shenzhen", 1, 7, 6994063.0, 100032, 1053.0, 1053.0, 1), Row("1AA100033", "wuhan", 1, 7, 8229807.0, 100033, 760.0, 760.0, 1), Row("1AA100034", "guangzhou", 1, 7, 5797079.0, 100034, 2061.0, 2061.0, 1), Row("1AA100035", "changsha", 1, 7, 6283062.0, 100035, 2142.0, 2142.0, 1), Row("1AA100036", "changsha", 1, 7, 8431770.0, 100036, 2224.0, 2224.0, 1), Row("1AA100037", "xiangtan", 1, 7, 3311312.0, 100037, 1015.0, 1015.0, 1), Row("1AA100038", "shenzhen", 1, 7, 2843881.0, 100038, 1229.0, 1229.0, 1), Row("1AA100039", "shenzhen", 1, 7, 1901889.0, 100039, 1750.0, 1750.0, 1), Row("1AA10004", "guangzhou", 1, 7, 3077303.0, 10004, 1717.0, 1717.0, 1), Row("1AA100040", "yichang", 1, 7, 7880439.0, 100040, 2078.0, 2078.0, 1), Row("1AA100041", "shenzhen", 1, 7, 3454331.0, 100041, 2734.0, 2734.0, 1), Row("1AA100042", "shenzhen", 1, 7, 23250.0, 100042, 2745.0, 2745.0, 1), Row("1AA100043", "guangzhou", 1, 7, 3278167.0, 100043, 571.0, 571.0, 1), Row("1AA100044", "guangzhou", 1, 7, 5659107.0, 100044, 1697.0, 1697.0, 1), Row("1AA100045", "xiangtan", 1, 7, 9952232.0, 100045, 2553.0, 2553.0, 1), Row("1AA100046", "guangzhou", 1, 7, 424923.0, 100046, 1077.0, 1077.0, 1), Row("1AA100047", "zhuzhou", 1, 7, 7839922.0, 100047, 1823.0, 1823.0, 1), Row("1AA100048", "guangzhou", 1, 7, 9500486.0, 100048, 2399.0, 2399.0, 1), Row("1AA100049", "guangzhou", 1, 7, 1952050.0, 100049, 2890.0, 2890.0, 1), Row("1AA10005", "xiangtan", 1, 7, 6190068.0, 10005, 1608.0, 1608.0, 1), Row("1AA100050", "yichang", 1, 7, 7768468.0, 100050, 29.0, 29.0, 1), Row("1AA100051", "guangzhou", 1, 7, 7236919.0, 100051, 1407.0, 1407.0, 1), Row("1AA100052", "zhuzhou", 1, 7, 167725.0, 100052, 845.0, 845.0, 1), Row("1AA100053", "wuhan", 1, 7, 2651084.0, 100053, 1655.0, 1655.0, 1), Row("1AA100054", "shenzhen", 1, 7, 6283156.0, 100054, 1368.0, 1368.0, 1), Row("1AA100055", "yichang", 1, 7, 7342321.0, 100055, 1728.0, 1728.0, 1), Row("1AA100056", "wuhan", 1, 7, 1753823.0, 100056, 750.0, 750.0, 1), Row("1AA100057", "zhuzhou", 1, 7, 5451533.0, 100057, 2288.0, 2288.0, 1), Row("1AA100058", "guangzhou", 1, 7, 5403108.0, 100058, 2635.0, 2635.0, 1), Row("1AA100059", "shenzhen", 1, 7, 168757.0, 100059, 1337.0, 1337.0, 1), Row("1AA10006", "guangzhou", 1, 7, 9394732.0, 10006, 2478.0, 2478.0, 1), Row("1AA100060", "xiangtan", 1, 7, 7420815.0, 100060, 538.0, 538.0, 1), Row("1AA100061", "changsha", 1, 7, 4358621.0, 100061, 1407.0, 1407.0, 1), Row("1AA100062", "yichang", 1, 7, 2362114.0, 100062, 2952.0, 2952.0, 1), Row("1AA100063", "yichang", 1, 7, 9318234.0, 100063, 1226.0, 1226.0, 1), Row("1AA100064", "zhuzhou", 1, 7, 5565240.0, 100064, 865.0, 865.0, 1), Row("1AA100065", "xiangtan", 1, 7, 3166724.0, 100065, 901.0, 901.0, 1), Row("1AA100066", "zhuzhou", 1, 7, 5592457.0, 100066, 1864.0, 1864.0, 1), Row("1AA100067", "wuhan", 1, 7, 7575196.0, 100067, 572.0, 572.0, 1), Row("1AA100068", "guangzhou", 1, 7, 3235086.0, 100068, 412.0, 412.0, 1), Row("1AA100069", "xiangtan", 1, 7, 7917206.0, 100069, 1491.0, 1491.0, 1), Row("1AA10007", "xiangtan", 1, 7, 4156339.0, 10007, 1350.0, 1350.0, 1), Row("1AA100070", "guangzhou", 1, 7, 4202614.0, 100070, 1567.0, 1567.0, 1), Row("1AA100071", "guangzhou", 1, 7, 2199957.0, 100071, 1973.0, 1973.0, 1), Row("1AA100072", "changsha", 1, 7, 511128.0, 100072, 448.0, 448.0, 1), Row("1AA100073", "zhuzhou", 1, 7, 580612.0, 100073, 2488.0, 2488.0, 1), Row("1AA100074", "wuhan", 1, 7, 275342.0, 100074, 907.0, 907.0, 1), Row("1AA100075", "shenzhen", 1, 7, 3215327.0, 100075, 2507.0, 2507.0, 1), Row("1AA100076", "wuhan", 1, 7, 8069859.0, 100076, 732.0, 732.0, 1), Row("1AA100077", "yichang", 1, 7, 6383562.0, 100077, 2077.0, 2077.0, 1), Row("1AA100078", "yichang", 1, 7, 6428516.0, 100078, 1434.0, 1434.0, 1), Row("1AA100079", "xiangtan", 1, 7, 5159121.0, 100079, 1098.0, 1098.0, 1), Row("1AA10008", "shenzhen", 1, 7, 3360388.0, 10008, 813.0, 813.0, 1), Row("1AA100080", "shenzhen", 1, 7, 5152985.0, 100080, 954.0, 954.0, 1), Row("1AA100081", "shenzhen", 1, 7, 3335480.0, 100081, 613.0, 613.0, 1), Row("1AA100082", "xiangtan", 1, 7, 994815.0, 100082, 2348.0, 2348.0, 1), Row("1AA100083", "zhuzhou", 1, 7, 507229.0, 100083, 2192.0, 2192.0, 1), Row("1AA100084", "guangzhou", 1, 7, 8976568.0, 100084, 2826.0, 2826.0, 1)))
  })

  //TC_334
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series = \"7Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series = \"7Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      Seq(Row("7Series", "1AA1", "yichang", 2738.562, 1, 2015), Row("7Series", "1AA10", "yichang", 1714.635, 10, 2015), Row("7Series", "1AA10000", "guangzhou", 2175.0, 10000, 2015), Row("7Series", "1AA1000000", "yichang", 1600.0, 1000000, 2015), Row("7Series", "1AA100026", "yichang", 1768.0, 100026, 2015), Row("7Series", "1AA10003", "xiangtan", 2071.0, 10003, 2015), Row("7Series", "1AA100030", "zhuzhou", 1333.0, 100030, 2015), Row("7Series", "1AA100031", "yichang", 1080.0, 100031, 2015), Row("7Series", "1AA100037", "xiangtan", 1015.0, 100037, 2015), Row("7Series", "1AA100054", "shenzhen", 1368.0, 100054, 2015), Row("7Series", "1AA100055", "yichang", 1728.0, 100055, 2015)))
  })

  //TC_335
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series > \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series > \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      Seq(Row("6Series", "1AA100006", "changsha", 2572.0, 100006, 2015), Row("6Series", "1AA100024", "changsha", 2483.0, 100024, 2015), Row("6Series", "1AA100038", "shenzhen", 1229.0, 100038, 2015), Row("6Series", "1AA100052", "zhuzhou", 845.0, 100052, 2015), Row("6Series", "1AA100056", "wuhan", 750.0, 100056, 2015), Row("6Series", "1AA100061", "changsha", 1407.0, 100061, 2015), Row("6Series", "1AA100064", "zhuzhou", 865.0, 100064, 2015), Row("6Series", "1AA100066", "zhuzhou", 1864.0, 100066, 2015), Row("6Series", "1AA100074", "wuhan", 907.0, 100074, 2015), Row("7Series", "1AA1", "yichang", 2738.562, 1, 2015), Row("7Series", "1AA10", "yichang", 1714.635, 10, 2015), Row("7Series", "1AA10000", "guangzhou", 2175.0, 10000, 2015), Row("7Series", "1AA1000000", "yichang", 1600.0, 1000000, 2015), Row("7Series", "1AA100026", "yichang", 1768.0, 100026, 2015), Row("7Series", "1AA10003", "xiangtan", 2071.0, 10003, 2015), Row("7Series", "1AA100030", "zhuzhou", 1333.0, 100030, 2015), Row("7Series", "1AA100031", "yichang", 1080.0, 100031, 2015), Row("7Series", "1AA100037", "xiangtan", 1015.0, 100037, 2015), Row("7Series", "1AA100054", "shenzhen", 1368.0, 100054, 2015), Row("7Series", "1AA100055", "yichang", 1728.0, 100055, 2015), Row("8Series", "1AA100008", "changsha", 1442.0, 100008, 2015), Row("8Series", "1AA100018", "yichang", 441.0, 100018, 2015), Row("8Series", "1AA100033", "wuhan", 760.0, 100033, 2015), Row("8Series", "1AA100039", "shenzhen", 1750.0, 100039, 2015), Row("8Series", "1AA100040", "yichang", 2078.0, 100040, 2015), Row("8Series", "1AA100044", "guangzhou", 1697.0, 100044, 2015), Row("8Series", "1AA10005", "xiangtan", 1608.0, 10005, 2015), Row("8Series", "1AA100060", "xiangtan", 538.0, 100060, 2015), Row("8Series", "1AA100068", "guangzhou", 412.0, 100068, 2015), Row("8Series", "1AA100069", "xiangtan", 1491.0, 100069, 2015), Row("8Series", "1AA10007", "xiangtan", 1350.0, 10007, 2015), Row("9Series", "1AA100000", "wuhan", 136.0, 100000, 2015), Row("9Series", "1AA100007", "changsha", 1991.0, 100007, 2015), Row("9Series", "1AA100017", "xiangtan", 2205.0, 100017, 2015), Row("9Series", "1AA100043", "guangzhou", 571.0, 100043, 2015), Row("9Series", "1AA100047", "zhuzhou", 1823.0, 100047, 2015), Row("9Series", "1AA100057", "zhuzhou", 2288.0, 100057, 2015), Row("9Series", "1AA100062", "yichang", 2952.0, 100062, 2015), Row("9Series", "1AA100080", "shenzhen", 954.0, 100080, 2015)))
  })

  //TC_336
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series >= \"4Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series >= \"4Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      Seq(Row("4Series", "1AA100004", "yichang", 2970.0, 100004, 2015), Row("4Series", "1AA100012", "xiangtan", 568.0, 100012, 2015), Row("4Series", "1AA100015", "xiangtan", 2863.0, 100015, 2015), Row("4Series", "1AA100059", "shenzhen", 1337.0, 100059, 2015), Row("4Series", "1AA100067", "wuhan", 572.0, 100067, 2015), Row("4Series", "1AA100072", "changsha", 448.0, 100072, 2015), Row("4Series", "1AA100073", "zhuzhou", 2488.0, 100073, 2015), Row("4Series", "1AA100079", "xiangtan", 1098.0, 100079, 2015), Row("5Series", "1AA100", "xiangtan", 1271.0, 100, 2015), Row("5Series", "1AA1000", "wuhan", 692.0, 1000, 2015), Row("5Series", "1AA100003", "zhuzhou", 2239.0, 100003, 2015), Row("5Series", "1AA100014", "zhuzhou", 151.0, 100014, 2015), Row("5Series", "1AA100019", "zhuzhou", 2194.0, 100019, 2015), Row("5Series", "1AA100020", "shenzhen", 256.0, 100020, 2015), Row("5Series", "1AA100022", "zhuzhou", 1999.0, 100022, 2015), Row("5Series", "1AA100023", "guangzhou", 2194.0, 100023, 2015), Row("5Series", "1AA100028", "zhuzhou", 2849.0, 100028, 2015), Row("5Series", "1AA100035", "changsha", 2142.0, 100035, 2015), Row("5Series", "1AA100036", "changsha", 2224.0, 100036, 2015), Row("5Series", "1AA10004", "guangzhou", 1717.0, 10004, 2015), Row("5Series", "1AA100041", "shenzhen", 2734.0, 100041, 2015), Row("5Series", "1AA100058", "guangzhou", 2635.0, 100058, 2015), Row("5Series", "1AA10008", "shenzhen", 813.0, 10008, 2015), Row("5Series", "1AA100081", "shenzhen", 613.0, 100081, 2015), Row("5Series", "1AA100082", "xiangtan", 2348.0, 100082, 2015), Row("6Series", "1AA100006", "changsha", 2572.0, 100006, 2015), Row("6Series", "1AA100024", "changsha", 2483.0, 100024, 2015), Row("6Series", "1AA100038", "shenzhen", 1229.0, 100038, 2015), Row("6Series", "1AA100052", "zhuzhou", 845.0, 100052, 2015), Row("6Series", "1AA100056", "wuhan", 750.0, 100056, 2015), Row("6Series", "1AA100061", "changsha", 1407.0, 100061, 2015), Row("6Series", "1AA100064", "zhuzhou", 865.0, 100064, 2015), Row("6Series", "1AA100066", "zhuzhou", 1864.0, 100066, 2015), Row("6Series", "1AA100074", "wuhan", 907.0, 100074, 2015), Row("7Series", "1AA1", "yichang", 2738.562, 1, 2015), Row("7Series", "1AA10", "yichang", 1714.635, 10, 2015), Row("7Series", "1AA10000", "guangzhou", 2175.0, 10000, 2015), Row("7Series", "1AA1000000", "yichang", 1600.0, 1000000, 2015), Row("7Series", "1AA100026", "yichang", 1768.0, 100026, 2015), Row("7Series", "1AA10003", "xiangtan", 2071.0, 10003, 2015), Row("7Series", "1AA100030", "zhuzhou", 1333.0, 100030, 2015), Row("7Series", "1AA100031", "yichang", 1080.0, 100031, 2015), Row("7Series", "1AA100037", "xiangtan", 1015.0, 100037, 2015), Row("7Series", "1AA100054", "shenzhen", 1368.0, 100054, 2015), Row("7Series", "1AA100055", "yichang", 1728.0, 100055, 2015), Row("8Series", "1AA100008", "changsha", 1442.0, 100008, 2015), Row("8Series", "1AA100018", "yichang", 441.0, 100018, 2015), Row("8Series", "1AA100033", "wuhan", 760.0, 100033, 2015), Row("8Series", "1AA100039", "shenzhen", 1750.0, 100039, 2015), Row("8Series", "1AA100040", "yichang", 2078.0, 100040, 2015), Row("8Series", "1AA100044", "guangzhou", 1697.0, 100044, 2015), Row("8Series", "1AA10005", "xiangtan", 1608.0, 10005, 2015), Row("8Series", "1AA100060", "xiangtan", 538.0, 100060, 2015), Row("8Series", "1AA100068", "guangzhou", 412.0, 100068, 2015), Row("8Series", "1AA100069", "xiangtan", 1491.0, 100069, 2015), Row("8Series", "1AA10007", "xiangtan", 1350.0, 10007, 2015), Row("9Series", "1AA100000", "wuhan", 136.0, 100000, 2015), Row("9Series", "1AA100007", "changsha", 1991.0, 100007, 2015), Row("9Series", "1AA100017", "xiangtan", 2205.0, 100017, 2015), Row("9Series", "1AA100043", "guangzhou", 571.0, 100043, 2015), Row("9Series", "1AA100047", "zhuzhou", 1823.0, 100047, 2015), Row("9Series", "1AA100057", "zhuzhou", 2288.0, 100057, 2015), Row("9Series", "1AA100062", "yichang", 2952.0, 100062, 2015), Row("9Series", "1AA100080", "shenzhen", 954.0, 100080, 2015)))
  })

  //TC_337
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series < \"3Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series < \"3Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      Seq(Row("0Series", "1AA100001", "xiangtan", 505.0, 100001, 2015), Row("0Series", "1AA100002", "changsha", 1341.0, 100002, 2015), Row("0Series", "1AA100009", "yichang", 1841.0, 100009, 2015), Row("0Series", "1AA100011", "guangzhou", 202.0, 100011, 2015), Row("0Series", "1AA10002", "wuhan", 2972.0, 10002, 2015), Row("0Series", "1AA100021", "changsha", 1778.0, 100021, 2015), Row("0Series", "1AA100025", "guangzhou", 1724.0, 100025, 2015), Row("0Series", "1AA100027", "zhuzhou", 2436.0, 100027, 2015), Row("0Series", "1AA100049", "guangzhou", 2890.0, 100049, 2015), Row("0Series", "1AA100065", "xiangtan", 901.0, 100065, 2015), Row("0Series", "1AA100070", "guangzhou", 1567.0, 100070, 2015), Row("0Series", "1AA100071", "guangzhou", 1973.0, 100071, 2015), Row("0Series", "1AA100076", "wuhan", 732.0, 100076, 2015), Row("0Series", "1AA100083", "zhuzhou", 2192.0, 100083, 2015), Row("0Series", "1AA100084", "guangzhou", 2826.0, 100084, 2015), Row("1Series", "1AA100005", "yichang", 2593.0, 100005, 2015), Row("1Series", "1AA100013", "changsha", 355.0, 100013, 2015), Row("1Series", "1AA100032", "shenzhen", 1053.0, 100032, 2015), Row("2Series", "1AA10001", "changsha", 298.0, 10001, 2015), Row("2Series", "1AA100029", "xiangtan", 1691.0, 100029, 2015), Row("2Series", "1AA100034", "guangzhou", 2061.0, 100034, 2015), Row("2Series", "1AA100045", "xiangtan", 2553.0, 100045, 2015), Row("2Series", "1AA100050", "yichang", 29.0, 100050, 2015), Row("2Series", "1AA100051", "guangzhou", 1407.0, 100051, 2015), Row("2Series", "1AA100053", "wuhan", 1655.0, 100053, 2015), Row("2Series", "1AA100063", "yichang", 1226.0, 100063, 2015), Row("2Series", "1AA100078", "yichang", 1434.0, 100078, 2015)))
  })

  //TC_338
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series <= \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series <= \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      Seq(Row("0Series", "1AA100001", "xiangtan", 505.0, 100001, 2015), Row("0Series", "1AA100002", "changsha", 1341.0, 100002, 2015), Row("0Series", "1AA100009", "yichang", 1841.0, 100009, 2015), Row("0Series", "1AA100011", "guangzhou", 202.0, 100011, 2015), Row("0Series", "1AA10002", "wuhan", 2972.0, 10002, 2015), Row("0Series", "1AA100021", "changsha", 1778.0, 100021, 2015), Row("0Series", "1AA100025", "guangzhou", 1724.0, 100025, 2015), Row("0Series", "1AA100027", "zhuzhou", 2436.0, 100027, 2015), Row("0Series", "1AA100049", "guangzhou", 2890.0, 100049, 2015), Row("0Series", "1AA100065", "xiangtan", 901.0, 100065, 2015), Row("0Series", "1AA100070", "guangzhou", 1567.0, 100070, 2015), Row("0Series", "1AA100071", "guangzhou", 1973.0, 100071, 2015), Row("0Series", "1AA100076", "wuhan", 732.0, 100076, 2015), Row("0Series", "1AA100083", "zhuzhou", 2192.0, 100083, 2015), Row("0Series", "1AA100084", "guangzhou", 2826.0, 100084, 2015), Row("1Series", "1AA100005", "yichang", 2593.0, 100005, 2015), Row("1Series", "1AA100013", "changsha", 355.0, 100013, 2015), Row("1Series", "1AA100032", "shenzhen", 1053.0, 100032, 2015), Row("2Series", "1AA10001", "changsha", 298.0, 10001, 2015), Row("2Series", "1AA100029", "xiangtan", 1691.0, 100029, 2015), Row("2Series", "1AA100034", "guangzhou", 2061.0, 100034, 2015), Row("2Series", "1AA100045", "xiangtan", 2553.0, 100045, 2015), Row("2Series", "1AA100050", "yichang", 29.0, 100050, 2015), Row("2Series", "1AA100051", "guangzhou", 1407.0, 100051, 2015), Row("2Series", "1AA100053", "wuhan", 1655.0, 100053, 2015), Row("2Series", "1AA100063", "yichang", 1226.0, 100063, 2015), Row("2Series", "1AA100078", "yichang", 1434.0, 100078, 2015), Row("3Series", "1AA100010", "zhuzhou", 79.0, 100010, 2015), Row("3Series", "1AA100016", "changsha", 1873.0, 100016, 2015), Row("3Series", "1AA100042", "shenzhen", 2745.0, 100042, 2015), Row("3Series", "1AA100046", "guangzhou", 1077.0, 100046, 2015), Row("3Series", "1AA100048", "guangzhou", 2399.0, 100048, 2015), Row("3Series", "1AA10006", "guangzhou", 2478.0, 10006, 2015), Row("3Series", "1AA100075", "shenzhen", 2507.0, 100075, 2015), Row("3Series", "1AA100077", "yichang", 2077.0, 100077, 2015), Row("4Series", "1AA100004", "yichang", 2970.0, 100004, 2015), Row("4Series", "1AA100012", "xiangtan", 568.0, 100012, 2015), Row("4Series", "1AA100015", "xiangtan", 2863.0, 100015, 2015), Row("4Series", "1AA100059", "shenzhen", 1337.0, 100059, 2015), Row("4Series", "1AA100067", "wuhan", 572.0, 100067, 2015), Row("4Series", "1AA100072", "changsha", 448.0, 100072, 2015), Row("4Series", "1AA100073", "zhuzhou", 2488.0, 100073, 2015), Row("4Series", "1AA100079", "xiangtan", 1098.0, 100079, 2015), Row("5Series", "1AA100", "xiangtan", 1271.0, 100, 2015), Row("5Series", "1AA1000", "wuhan", 692.0, 1000, 2015), Row("5Series", "1AA100003", "zhuzhou", 2239.0, 100003, 2015), Row("5Series", "1AA100014", "zhuzhou", 151.0, 100014, 2015), Row("5Series", "1AA100019", "zhuzhou", 2194.0, 100019, 2015), Row("5Series", "1AA100020", "shenzhen", 256.0, 100020, 2015), Row("5Series", "1AA100022", "zhuzhou", 1999.0, 100022, 2015), Row("5Series", "1AA100023", "guangzhou", 2194.0, 100023, 2015), Row("5Series", "1AA100028", "zhuzhou", 2849.0, 100028, 2015), Row("5Series", "1AA100035", "changsha", 2142.0, 100035, 2015), Row("5Series", "1AA100036", "changsha", 2224.0, 100036, 2015), Row("5Series", "1AA10004", "guangzhou", 1717.0, 10004, 2015), Row("5Series", "1AA100041", "shenzhen", 2734.0, 100041, 2015), Row("5Series", "1AA100058", "guangzhou", 2635.0, 100058, 2015), Row("5Series", "1AA10008", "shenzhen", 813.0, 10008, 2015), Row("5Series", "1AA100081", "shenzhen", 613.0, 100081, 2015), Row("5Series", "1AA100082", "xiangtan", 2348.0, 100082, 2015)))
  })

  //TC_339
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE deliveryCity LIKE '%wuhan%' GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE deliveryCity LIKE '%wuhan%' GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      Seq(Row("0Series", "1AA10002", "wuhan", 2972.0, 10002, 2015), Row("0Series", "1AA100076", "wuhan", 732.0, 100076, 2015), Row("2Series", "1AA100053", "wuhan", 1655.0, 100053, 2015), Row("4Series", "1AA100067", "wuhan", 572.0, 100067, 2015), Row("5Series", "1AA1000", "wuhan", 692.0, 1000, 2015), Row("6Series", "1AA100056", "wuhan", 750.0, 100056, 2015), Row("6Series", "1AA100074", "wuhan", 907.0, 100074, 2015), Row("8Series", "1AA100033", "wuhan", 760.0, 100033, 2015), Row("9Series", "1AA100000", "wuhan", 136.0, 100000, 2015)))
  })

  //TC_350
  test("SELECT imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE NOT(deliveryCity = \"wuhan\") GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE NOT(deliveryCity = \"wuhan\") GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      Seq(Row("1AA1", "yichang", 2738.562, 2015, 1), Row("1AA10", "yichang", 1714.635, 2015, 10), Row("1AA100", "xiangtan", 1271.0, 2015, 100), Row("1AA10000", "guangzhou", 2175.0, 2015, 10000), Row("1AA1000000", "yichang", 1600.0, 2015, 1000000), Row("1AA100001", "xiangtan", 505.0, 2015, 100001), Row("1AA100002", "changsha", 1341.0, 2015, 100002), Row("1AA100003", "zhuzhou", 2239.0, 2015, 100003), Row("1AA100004", "yichang", 2970.0, 2015, 100004), Row("1AA100005", "yichang", 2593.0, 2015, 100005), Row("1AA100006", "changsha", 2572.0, 2015, 100006), Row("1AA100007", "changsha", 1991.0, 2015, 100007), Row("1AA100008", "changsha", 1442.0, 2015, 100008), Row("1AA100009", "yichang", 1841.0, 2015, 100009), Row("1AA10001", "changsha", 298.0, 2015, 10001), Row("1AA100010", "zhuzhou", 79.0, 2015, 100010), Row("1AA100011", "guangzhou", 202.0, 2015, 100011), Row("1AA100012", "xiangtan", 568.0, 2015, 100012), Row("1AA100013", "changsha", 355.0, 2015, 100013), Row("1AA100014", "zhuzhou", 151.0, 2015, 100014), Row("1AA100015", "xiangtan", 2863.0, 2015, 100015), Row("1AA100016", "changsha", 1873.0, 2015, 100016), Row("1AA100017", "xiangtan", 2205.0, 2015, 100017), Row("1AA100018", "yichang", 441.0, 2015, 100018), Row("1AA100019", "zhuzhou", 2194.0, 2015, 100019), Row("1AA100020", "shenzhen", 256.0, 2015, 100020), Row("1AA100021", "changsha", 1778.0, 2015, 100021), Row("1AA100022", "zhuzhou", 1999.0, 2015, 100022), Row("1AA100023", "guangzhou", 2194.0, 2015, 100023), Row("1AA100024", "changsha", 2483.0, 2015, 100024), Row("1AA100025", "guangzhou", 1724.0, 2015, 100025), Row("1AA100026", "yichang", 1768.0, 2015, 100026), Row("1AA100027", "zhuzhou", 2436.0, 2015, 100027), Row("1AA100028", "zhuzhou", 2849.0, 2015, 100028), Row("1AA100029", "xiangtan", 1691.0, 2015, 100029), Row("1AA10003", "xiangtan", 2071.0, 2015, 10003), Row("1AA100030", "zhuzhou", 1333.0, 2015, 100030), Row("1AA100031", "yichang", 1080.0, 2015, 100031), Row("1AA100032", "shenzhen", 1053.0, 2015, 100032), Row("1AA100034", "guangzhou", 2061.0, 2015, 100034), Row("1AA100035", "changsha", 2142.0, 2015, 100035), Row("1AA100036", "changsha", 2224.0, 2015, 100036), Row("1AA100037", "xiangtan", 1015.0, 2015, 100037), Row("1AA100038", "shenzhen", 1229.0, 2015, 100038), Row("1AA100039", "shenzhen", 1750.0, 2015, 100039), Row("1AA10004", "guangzhou", 1717.0, 2015, 10004), Row("1AA100040", "yichang", 2078.0, 2015, 100040), Row("1AA100041", "shenzhen", 2734.0, 2015, 100041), Row("1AA100042", "shenzhen", 2745.0, 2015, 100042), Row("1AA100043", "guangzhou", 571.0, 2015, 100043), Row("1AA100044", "guangzhou", 1697.0, 2015, 100044), Row("1AA100045", "xiangtan", 2553.0, 2015, 100045), Row("1AA100046", "guangzhou", 1077.0, 2015, 100046), Row("1AA100047", "zhuzhou", 1823.0, 2015, 100047), Row("1AA100048", "guangzhou", 2399.0, 2015, 100048), Row("1AA100049", "guangzhou", 2890.0, 2015, 100049), Row("1AA10005", "xiangtan", 1608.0, 2015, 10005), Row("1AA100050", "yichang", 29.0, 2015, 100050), Row("1AA100051", "guangzhou", 1407.0, 2015, 100051), Row("1AA100052", "zhuzhou", 845.0, 2015, 100052), Row("1AA100054", "shenzhen", 1368.0, 2015, 100054), Row("1AA100055", "yichang", 1728.0, 2015, 100055), Row("1AA100057", "zhuzhou", 2288.0, 2015, 100057), Row("1AA100058", "guangzhou", 2635.0, 2015, 100058), Row("1AA100059", "shenzhen", 1337.0, 2015, 100059), Row("1AA10006", "guangzhou", 2478.0, 2015, 10006), Row("1AA100060", "xiangtan", 538.0, 2015, 100060), Row("1AA100061", "changsha", 1407.0, 2015, 100061), Row("1AA100062", "yichang", 2952.0, 2015, 100062), Row("1AA100063", "yichang", 1226.0, 2015, 100063), Row("1AA100064", "zhuzhou", 865.0, 2015, 100064), Row("1AA100065", "xiangtan", 901.0, 2015, 100065), Row("1AA100066", "zhuzhou", 1864.0, 2015, 100066), Row("1AA100068", "guangzhou", 412.0, 2015, 100068), Row("1AA100069", "xiangtan", 1491.0, 2015, 100069), Row("1AA10007", "xiangtan", 1350.0, 2015, 10007), Row("1AA100070", "guangzhou", 1567.0, 2015, 100070), Row("1AA100071", "guangzhou", 1973.0, 2015, 100071), Row("1AA100072", "changsha", 448.0, 2015, 100072), Row("1AA100073", "zhuzhou", 2488.0, 2015, 100073), Row("1AA100075", "shenzhen", 2507.0, 2015, 100075), Row("1AA100077", "yichang", 2077.0, 2015, 100077), Row("1AA100078", "yichang", 1434.0, 2015, 100078), Row("1AA100079", "xiangtan", 1098.0, 2015, 100079), Row("1AA10008", "shenzhen", 813.0, 2015, 10008), Row("1AA100080", "shenzhen", 954.0, 2015, 100080), Row("1AA100081", "shenzhen", 613.0, 2015, 100081), Row("1AA100082", "xiangtan", 2348.0, 2015, 100082), Row("1AA100083", "zhuzhou", 2192.0, 2015, 100083), Row("1AA100084", "guangzhou", 2826.0, 2015, 100084)))
  })

  //TC_365
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_366
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, AVG(deviceInformationId) AS Avg_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, AVG(deviceInformationId) AS Avg_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1.0, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10.0, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100.0, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000.0, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000.0, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000.0, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000.0, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001.0, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002.0, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003.0, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004.0, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005.0, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006.0, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007.0, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008.0, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009.0, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001.0, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010.0, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011.0, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012.0, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013.0, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014.0, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015.0, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016.0, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017.0, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018.0, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019.0, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002.0, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020.0, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021.0, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022.0, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023.0, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024.0, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025.0, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026.0, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027.0, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028.0, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029.0, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003.0, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030.0, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031.0, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032.0, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033.0, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034.0, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035.0, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036.0, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037.0, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038.0, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039.0, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004.0, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040.0, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041.0, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042.0, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043.0, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044.0, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045.0, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046.0, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047.0, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048.0, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049.0, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005.0, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050.0, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051.0, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052.0, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053.0, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054.0, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055.0, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056.0, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057.0, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058.0, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059.0, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006.0, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060.0, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061.0, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062.0, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063.0, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064.0, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065.0, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066.0, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067.0, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068.0, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069.0, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007.0, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070.0, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071.0, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072.0, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073.0, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074.0, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075.0, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076.0, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077.0, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078.0, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079.0, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008.0, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080.0, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081.0, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082.0, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083.0, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084.0, 2826.0)))
  })

  //TC_367
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(deviceInformationId) AS Count_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(deviceInformationId) AS Count_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 1, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 1, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 1, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 1, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 1, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 1, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 1, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 1, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 1, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 1, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 1, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 1, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 1, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 1, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 1, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 1, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 1, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 1, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 1, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 1, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 1, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 1, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 1, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 1, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 1, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 1, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 1, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 1, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 1, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 1, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 1, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 1, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 1, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 1, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 1, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 1, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 1, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 1, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 1, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 1, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 1, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 1, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 1, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 1, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 1, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 1, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 1, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 1, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 1, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 1, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 1, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 1, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 1, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 1, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 1, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 1, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 1, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 1, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 1, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 1, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 1, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 1, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 1, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 1, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 1, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 1, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 1, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 1, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 1, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 1, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 1, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 1, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 1, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 1, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 1, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 1, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 1, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 1, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 1, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 1, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 1, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 1, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 1, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 1, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 1, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 1, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 1, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 1, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 1, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 1, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 1, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 1, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 1, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 1, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 1, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 1, 2826.0)))
  })

  //TC_368
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(DISTINCT deviceInformationId) AS LONG_COL_0, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(DISTINCT deviceInformationId) AS LONG_COL_0, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 1, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 1, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 1, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 1, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 1, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 1, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 1, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 1, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 1, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 1, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 1, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 1, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 1, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 1, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 1, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 1, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 1, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 1, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 1, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 1, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 1, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 1, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 1, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 1, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 1, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 1, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 1, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 1, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 1, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 1, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 1, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 1, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 1, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 1, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 1, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 1, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 1, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 1, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 1, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 1, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 1, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 1, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 1, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 1, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 1, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 1, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 1, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 1, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 1, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 1, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 1, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 1, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 1, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 1, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 1, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 1, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 1, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 1, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 1, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 1, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 1, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 1, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 1, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 1, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 1, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 1, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 1, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 1, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 1, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 1, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 1, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 1, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 1, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 1, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 1, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 1, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 1, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 1, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 1, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 1, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 1, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 1, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 1, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 1, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 1, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 1, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 1, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 1, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 1, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 1, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 1, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 1, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 1, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 1, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 1, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 1, 2826.0)))
  })

  //TC_370
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MAX(deviceInformationId) AS Max_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MAX(deviceInformationId) AS Max_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_371
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MIN(deviceInformationId) AS Min_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MIN(deviceInformationId) AS Min_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_374
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_375
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(gamePointId) AS Count_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(gamePointId) AS Count_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 1), Row("1AA10", "yichang", "7Series", 2015, 10, 1), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 1), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 1), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 1), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 1), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 1), Row("1AA100004", "yichang", "4Series", 2015, 100004, 1), Row("1AA100005", "yichang", "1Series", 2015, 100005, 1), Row("1AA100006", "changsha", "6Series", 2015, 100006, 1), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1), Row("1AA10001", "changsha", "2Series", 2015, 10001, 1), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 1), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 1), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 1), Row("1AA100013", "changsha", "1Series", 2015, 100013, 1), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 1), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 1), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 1), Row("1AA100018", "yichang", "8Series", 2015, 100018, 1), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 1), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 1), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 1), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 1), Row("1AA100024", "changsha", "6Series", 2015, 100024, 1), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 1), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 1), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 1), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 1), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 1), Row("1AA100035", "changsha", "5Series", 2015, 100035, 1), Row("1AA100036", "changsha", "5Series", 2015, 100036, 1), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1), Row("1AA100040", "yichang", "8Series", 2015, 100040, 1), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 1), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 1), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 1), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 1), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 1), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 1), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1), Row("1AA100050", "yichang", "2Series", 2015, 100050, 1), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 1), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 1), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 1), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 1), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 1), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 1), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1), Row("1AA100062", "yichang", "9Series", 2015, 100062, 1), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 1), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 1), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 1), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 1), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1), Row("1AA100072", "changsha", "4Series", 2015, 100072, 1), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 1), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 1), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 1), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 1), Row("1AA100077", "yichang", "3Series", 2015, 100077, 1), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 1), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 1), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 1), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 1), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 1), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 1)))
  })

  //TC_376
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(DISTINCT gamePointId) AS DistinctCount_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(DISTINCT gamePointId) AS DistinctCount_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 1), Row("1AA10", "yichang", "7Series", 2015, 10, 1), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 1), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 1), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 1), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 1), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 1), Row("1AA100004", "yichang", "4Series", 2015, 100004, 1), Row("1AA100005", "yichang", "1Series", 2015, 100005, 1), Row("1AA100006", "changsha", "6Series", 2015, 100006, 1), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1), Row("1AA10001", "changsha", "2Series", 2015, 10001, 1), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 1), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 1), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 1), Row("1AA100013", "changsha", "1Series", 2015, 100013, 1), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 1), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 1), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 1), Row("1AA100018", "yichang", "8Series", 2015, 100018, 1), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 1), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 1), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 1), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 1), Row("1AA100024", "changsha", "6Series", 2015, 100024, 1), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 1), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 1), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 1), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 1), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 1), Row("1AA100035", "changsha", "5Series", 2015, 100035, 1), Row("1AA100036", "changsha", "5Series", 2015, 100036, 1), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1), Row("1AA100040", "yichang", "8Series", 2015, 100040, 1), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 1), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 1), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 1), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 1), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 1), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 1), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1), Row("1AA100050", "yichang", "2Series", 2015, 100050, 1), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 1), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 1), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 1), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 1), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 1), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 1), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1), Row("1AA100062", "yichang", "9Series", 2015, 100062, 1), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 1), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 1), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 1), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 1), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1), Row("1AA100072", "changsha", "4Series", 2015, 100072, 1), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 1), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 1), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 1), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 1), Row("1AA100077", "yichang", "3Series", 2015, 100077, 1), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 1), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 1), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 1), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 1), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 1), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 1)))
  })

  //TC_377
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MAX(gamePointId) AS Max_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MAX(gamePointId) AS Max_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_378
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_379
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei DESC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei DESC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562)))
  })

  //TC_380
  test("SELECT deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(imei) AS Count_imei, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, series ORDER BY deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(imei) AS Count_imei, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, series ORDER BY deliveryCity ASC, series ASC"),
      Seq(Row("changsha", "0Series", 4030, 200023, 2, 1341.0), Row("changsha", "1Series", 2015, 100013, 1, 355.0), Row("changsha", "2Series", 2015, 10001, 1, 298.0), Row("changsha", "3Series", 2015, 100016, 1, 1873.0), Row("changsha", "4Series", 2015, 100072, 1, 448.0), Row("changsha", "5Series", 4030, 200071, 2, 2142.0), Row("changsha", "6Series", 6045, 300091, 3, 1407.0), Row("changsha", "8Series", 2015, 100008, 1, 1442.0), Row("changsha", "9Series", 2015, 100007, 1, 1991.0), Row("guangzhou", "0Series", 12090, 600310, 6, 202.0), Row("guangzhou", "2Series", 4030, 200085, 2, 1407.0), Row("guangzhou", "3Series", 6045, 210100, 3, 1077.0), Row("guangzhou", "5Series", 6045, 210085, 3, 1717.0), Row("guangzhou", "7Series", 2015, 10000, 1, 2175.0), Row("guangzhou", "8Series", 4030, 200112, 2, 412.0), Row("guangzhou", "9Series", 2015, 100043, 1, 571.0), Row("shenzhen", "1Series", 2015, 100032, 1, 1053.0), Row("shenzhen", "3Series", 4030, 200117, 2, 2507.0), Row("shenzhen", "4Series", 2015, 100059, 1, 1337.0), Row("shenzhen", "5Series", 8060, 310150, 4, 256.0), Row("shenzhen", "6Series", 2015, 100038, 1, 1229.0), Row("shenzhen", "7Series", 2015, 100054, 1, 1368.0), Row("shenzhen", "8Series", 2015, 100039, 1, 1750.0), Row("shenzhen", "9Series", 2015, 100080, 1, 954.0), Row("wuhan", "0Series", 4030, 110078, 2, 732.0), Row("wuhan", "2Series", 2015, 100053, 1, 1655.0), Row("wuhan", "4Series", 2015, 100067, 1, 572.0), Row("wuhan", "5Series", 2015, 1000, 1, 692.0), Row("wuhan", "6Series", 4030, 200130, 2, 750.0), Row("wuhan", "8Series", 2015, 100033, 1, 760.0), Row("wuhan", "9Series", 2015, 100000, 1, 136.0), Row("xiangtan", "0Series", 4030, 200066, 2, 505.0), Row("xiangtan", "2Series", 4030, 200074, 2, 1691.0), Row("xiangtan", "4Series", 6045, 300106, 3, 568.0), Row("xiangtan", "5Series", 4030, 100182, 2, 1271.0), Row("xiangtan", "7Series", 4030, 110040, 2, 1015.0), Row("xiangtan", "8Series", 8060, 220141, 4, 538.0), Row("xiangtan", "9Series", 2015, 100017, 1, 2205.0), Row("yichang", "0Series", 2015, 100009, 1, 1841.0), Row("yichang", "1Series", 2015, 100005, 1, 2593.0), Row("yichang", "2Series", 6045, 300191, 3, 29.0), Row("yichang", "3Series", 2015, 100077, 1, 2077.0), Row("yichang", "4Series", 2015, 100004, 1, 2970.0), Row("yichang", "7Series", 12090, 1300123, 6, 1080.0), Row("yichang", "8Series", 4030, 200058, 2, 441.0), Row("yichang", "9Series", 2015, 100062, 1, 2952.0), Row("zhuzhou", "0Series", 4030, 200110, 2, 2192.0), Row("zhuzhou", "3Series", 2015, 100010, 1, 79.0), Row("zhuzhou", "4Series", 2015, 100073, 1, 2488.0), Row("zhuzhou", "5Series", 10075, 500086, 5, 151.0), Row("zhuzhou", "6Series", 6045, 300182, 3, 845.0), Row("zhuzhou", "7Series", 2015, 100030, 1, 1333.0), Row("zhuzhou", "9Series", 4030, 200104, 2, 1823.0)))
  })

  //TC_381
  test("SELECT deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(DISTINCT imei) AS DistinctCount_imei, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, series ORDER BY deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(DISTINCT imei) AS DistinctCount_imei, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, series ORDER BY deliveryCity ASC, series ASC"),
      Seq(Row("changsha", "0Series", 4030, 200023, 2, 1341.0), Row("changsha", "1Series", 2015, 100013, 1, 355.0), Row("changsha", "2Series", 2015, 10001, 1, 298.0), Row("changsha", "3Series", 2015, 100016, 1, 1873.0), Row("changsha", "4Series", 2015, 100072, 1, 448.0), Row("changsha", "5Series", 4030, 200071, 2, 2142.0), Row("changsha", "6Series", 6045, 300091, 3, 1407.0), Row("changsha", "8Series", 2015, 100008, 1, 1442.0), Row("changsha", "9Series", 2015, 100007, 1, 1991.0), Row("guangzhou", "0Series", 12090, 600310, 6, 202.0), Row("guangzhou", "2Series", 4030, 200085, 2, 1407.0), Row("guangzhou", "3Series", 6045, 210100, 3, 1077.0), Row("guangzhou", "5Series", 6045, 210085, 3, 1717.0), Row("guangzhou", "7Series", 2015, 10000, 1, 2175.0), Row("guangzhou", "8Series", 4030, 200112, 2, 412.0), Row("guangzhou", "9Series", 2015, 100043, 1, 571.0), Row("shenzhen", "1Series", 2015, 100032, 1, 1053.0), Row("shenzhen", "3Series", 4030, 200117, 2, 2507.0), Row("shenzhen", "4Series", 2015, 100059, 1, 1337.0), Row("shenzhen", "5Series", 8060, 310150, 4, 256.0), Row("shenzhen", "6Series", 2015, 100038, 1, 1229.0), Row("shenzhen", "7Series", 2015, 100054, 1, 1368.0), Row("shenzhen", "8Series", 2015, 100039, 1, 1750.0), Row("shenzhen", "9Series", 2015, 100080, 1, 954.0), Row("wuhan", "0Series", 4030, 110078, 2, 732.0), Row("wuhan", "2Series", 2015, 100053, 1, 1655.0), Row("wuhan", "4Series", 2015, 100067, 1, 572.0), Row("wuhan", "5Series", 2015, 1000, 1, 692.0), Row("wuhan", "6Series", 4030, 200130, 2, 750.0), Row("wuhan", "8Series", 2015, 100033, 1, 760.0), Row("wuhan", "9Series", 2015, 100000, 1, 136.0), Row("xiangtan", "0Series", 4030, 200066, 2, 505.0), Row("xiangtan", "2Series", 4030, 200074, 2, 1691.0), Row("xiangtan", "4Series", 6045, 300106, 3, 568.0), Row("xiangtan", "5Series", 4030, 100182, 2, 1271.0), Row("xiangtan", "7Series", 4030, 110040, 2, 1015.0), Row("xiangtan", "8Series", 8060, 220141, 4, 538.0), Row("xiangtan", "9Series", 2015, 100017, 1, 2205.0), Row("yichang", "0Series", 2015, 100009, 1, 1841.0), Row("yichang", "1Series", 2015, 100005, 1, 2593.0), Row("yichang", "2Series", 6045, 300191, 3, 29.0), Row("yichang", "3Series", 2015, 100077, 1, 2077.0), Row("yichang", "4Series", 2015, 100004, 1, 2970.0), Row("yichang", "7Series", 12090, 1300123, 6, 1080.0), Row("yichang", "8Series", 4030, 200058, 2, 441.0), Row("yichang", "9Series", 2015, 100062, 1, 2952.0), Row("zhuzhou", "0Series", 4030, 200110, 2, 2192.0), Row("zhuzhou", "3Series", 2015, 100010, 1, 79.0), Row("zhuzhou", "4Series", 2015, 100073, 1, 2488.0), Row("zhuzhou", "5Series", 10075, 500086, 5, 151.0), Row("zhuzhou", "6Series", 6045, 300182, 3, 845.0), Row("zhuzhou", "7Series", 2015, 100030, 1, 1333.0), Row("zhuzhou", "9Series", 4030, 200104, 2, 1823.0)))
  })

  //TC_382
  test("SELECT deliveryCity, Latest_YEAR, imei, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, Latest_YEAR, imei ORDER BY deliveryCity ASC, Latest_YEAR ASC, imei ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, Latest_YEAR, imei, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, Latest_YEAR, imei ORDER BY deliveryCity ASC, Latest_YEAR ASC, imei ASC"),
      Seq(Row("changsha", 2015, "1AA100002", 1341.0, 100002), Row("changsha", 2015, "1AA100006", 2572.0, 100006), Row("changsha", 2015, "1AA100007", 1991.0, 100007), Row("changsha", 2015, "1AA100008", 1442.0, 100008), Row("changsha", 2015, "1AA10001", 298.0, 10001), Row("changsha", 2015, "1AA100013", 355.0, 100013), Row("changsha", 2015, "1AA100016", 1873.0, 100016), Row("changsha", 2015, "1AA100021", 1778.0, 100021), Row("changsha", 2015, "1AA100024", 2483.0, 100024), Row("changsha", 2015, "1AA100035", 2142.0, 100035), Row("changsha", 2015, "1AA100036", 2224.0, 100036), Row("changsha", 2015, "1AA100061", 1407.0, 100061), Row("changsha", 2015, "1AA100072", 448.0, 100072), Row("guangzhou", 2015, "1AA10000", 2175.0, 10000), Row("guangzhou", 2015, "1AA100011", 202.0, 100011), Row("guangzhou", 2015, "1AA100023", 2194.0, 100023), Row("guangzhou", 2015, "1AA100025", 1724.0, 100025), Row("guangzhou", 2015, "1AA100034", 2061.0, 100034), Row("guangzhou", 2015, "1AA10004", 1717.0, 10004), Row("guangzhou", 2015, "1AA100043", 571.0, 100043), Row("guangzhou", 2015, "1AA100044", 1697.0, 100044), Row("guangzhou", 2015, "1AA100046", 1077.0, 100046), Row("guangzhou", 2015, "1AA100048", 2399.0, 100048), Row("guangzhou", 2015, "1AA100049", 2890.0, 100049), Row("guangzhou", 2015, "1AA100051", 1407.0, 100051), Row("guangzhou", 2015, "1AA100058", 2635.0, 100058), Row("guangzhou", 2015, "1AA10006", 2478.0, 10006), Row("guangzhou", 2015, "1AA100068", 412.0, 100068), Row("guangzhou", 2015, "1AA100070", 1567.0, 100070), Row("guangzhou", 2015, "1AA100071", 1973.0, 100071), Row("guangzhou", 2015, "1AA100084", 2826.0, 100084), Row("shenzhen", 2015, "1AA100020", 256.0, 100020), Row("shenzhen", 2015, "1AA100032", 1053.0, 100032), Row("shenzhen", 2015, "1AA100038", 1229.0, 100038), Row("shenzhen", 2015, "1AA100039", 1750.0, 100039), Row("shenzhen", 2015, "1AA100041", 2734.0, 100041), Row("shenzhen", 2015, "1AA100042", 2745.0, 100042), Row("shenzhen", 2015, "1AA100054", 1368.0, 100054), Row("shenzhen", 2015, "1AA100059", 1337.0, 100059), Row("shenzhen", 2015, "1AA100075", 2507.0, 100075), Row("shenzhen", 2015, "1AA10008", 813.0, 10008), Row("shenzhen", 2015, "1AA100080", 954.0, 100080), Row("shenzhen", 2015, "1AA100081", 613.0, 100081), Row("wuhan", 2015, "1AA1000", 692.0, 1000), Row("wuhan", 2015, "1AA100000", 136.0, 100000), Row("wuhan", 2015, "1AA10002", 2972.0, 10002), Row("wuhan", 2015, "1AA100033", 760.0, 100033), Row("wuhan", 2015, "1AA100053", 1655.0, 100053), Row("wuhan", 2015, "1AA100056", 750.0, 100056), Row("wuhan", 2015, "1AA100067", 572.0, 100067), Row("wuhan", 2015, "1AA100074", 907.0, 100074), Row("wuhan", 2015, "1AA100076", 732.0, 100076), Row("xiangtan", 2015, "1AA100", 1271.0, 100), Row("xiangtan", 2015, "1AA100001", 505.0, 100001), Row("xiangtan", 2015, "1AA100012", 568.0, 100012), Row("xiangtan", 2015, "1AA100015", 2863.0, 100015), Row("xiangtan", 2015, "1AA100017", 2205.0, 100017), Row("xiangtan", 2015, "1AA100029", 1691.0, 100029), Row("xiangtan", 2015, "1AA10003", 2071.0, 10003), Row("xiangtan", 2015, "1AA100037", 1015.0, 100037), Row("xiangtan", 2015, "1AA100045", 2553.0, 100045), Row("xiangtan", 2015, "1AA10005", 1608.0, 10005), Row("xiangtan", 2015, "1AA100060", 538.0, 100060), Row("xiangtan", 2015, "1AA100065", 901.0, 100065), Row("xiangtan", 2015, "1AA100069", 1491.0, 100069), Row("xiangtan", 2015, "1AA10007", 1350.0, 10007), Row("xiangtan", 2015, "1AA100079", 1098.0, 100079), Row("xiangtan", 2015, "1AA100082", 2348.0, 100082), Row("yichang", 2015, "1AA1", 2738.562, 1), Row("yichang", 2015, "1AA10", 1714.635, 10), Row("yichang", 2015, "1AA1000000", 1600.0, 1000000), Row("yichang", 2015, "1AA100004", 2970.0, 100004), Row("yichang", 2015, "1AA100005", 2593.0, 100005), Row("yichang", 2015, "1AA100009", 1841.0, 100009), Row("yichang", 2015, "1AA100018", 441.0, 100018), Row("yichang", 2015, "1AA100026", 1768.0, 100026), Row("yichang", 2015, "1AA100031", 1080.0, 100031), Row("yichang", 2015, "1AA100040", 2078.0, 100040), Row("yichang", 2015, "1AA100050", 29.0, 100050), Row("yichang", 2015, "1AA100055", 1728.0, 100055), Row("yichang", 2015, "1AA100062", 2952.0, 100062), Row("yichang", 2015, "1AA100063", 1226.0, 100063), Row("yichang", 2015, "1AA100077", 2077.0, 100077), Row("yichang", 2015, "1AA100078", 1434.0, 100078), Row("zhuzhou", 2015, "1AA100003", 2239.0, 100003), Row("zhuzhou", 2015, "1AA100010", 79.0, 100010), Row("zhuzhou", 2015, "1AA100014", 151.0, 100014), Row("zhuzhou", 2015, "1AA100019", 2194.0, 100019), Row("zhuzhou", 2015, "1AA100022", 1999.0, 100022), Row("zhuzhou", 2015, "1AA100027", 2436.0, 100027), Row("zhuzhou", 2015, "1AA100028", 2849.0, 100028), Row("zhuzhou", 2015, "1AA100030", 1333.0, 100030), Row("zhuzhou", 2015, "1AA100047", 1823.0, 100047), Row("zhuzhou", 2015, "1AA100052", 845.0, 100052), Row("zhuzhou", 2015, "1AA100057", 2288.0, 100057), Row("zhuzhou", 2015, "1AA100064", 865.0, 100064), Row("zhuzhou", 2015, "1AA100066", 1864.0, 100066), Row("zhuzhou", 2015, "1AA100073", 2488.0, 100073), Row("zhuzhou", 2015, "1AA100083", 2192.0, 100083)))
  })

  //TC_383
  test("SELECT deliveryCity, imei, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, imei, series ORDER BY deliveryCity ASC, imei ASC, series ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, imei, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, imei, series ORDER BY deliveryCity ASC, imei ASC, series ASC"),
      Seq(Row("changsha", "1AA100002", "0Series", 2015), Row("changsha", "1AA100006", "6Series", 2015), Row("changsha", "1AA100007", "9Series", 2015), Row("changsha", "1AA100008", "8Series", 2015), Row("changsha", "1AA10001", "2Series", 2015), Row("changsha", "1AA100013", "1Series", 2015), Row("changsha", "1AA100016", "3Series", 2015), Row("changsha", "1AA100021", "0Series", 2015), Row("changsha", "1AA100024", "6Series", 2015), Row("changsha", "1AA100035", "5Series", 2015), Row("changsha", "1AA100036", "5Series", 2015), Row("changsha", "1AA100061", "6Series", 2015), Row("changsha", "1AA100072", "4Series", 2015), Row("guangzhou", "1AA10000", "7Series", 2015), Row("guangzhou", "1AA100011", "0Series", 2015), Row("guangzhou", "1AA100023", "5Series", 2015), Row("guangzhou", "1AA100025", "0Series", 2015), Row("guangzhou", "1AA100034", "2Series", 2015), Row("guangzhou", "1AA10004", "5Series", 2015), Row("guangzhou", "1AA100043", "9Series", 2015), Row("guangzhou", "1AA100044", "8Series", 2015), Row("guangzhou", "1AA100046", "3Series", 2015), Row("guangzhou", "1AA100048", "3Series", 2015), Row("guangzhou", "1AA100049", "0Series", 2015), Row("guangzhou", "1AA100051", "2Series", 2015), Row("guangzhou", "1AA100058", "5Series", 2015), Row("guangzhou", "1AA10006", "3Series", 2015), Row("guangzhou", "1AA100068", "8Series", 2015), Row("guangzhou", "1AA100070", "0Series", 2015), Row("guangzhou", "1AA100071", "0Series", 2015), Row("guangzhou", "1AA100084", "0Series", 2015), Row("shenzhen", "1AA100020", "5Series", 2015), Row("shenzhen", "1AA100032", "1Series", 2015), Row("shenzhen", "1AA100038", "6Series", 2015), Row("shenzhen", "1AA100039", "8Series", 2015), Row("shenzhen", "1AA100041", "5Series", 2015), Row("shenzhen", "1AA100042", "3Series", 2015), Row("shenzhen", "1AA100054", "7Series", 2015), Row("shenzhen", "1AA100059", "4Series", 2015), Row("shenzhen", "1AA100075", "3Series", 2015), Row("shenzhen", "1AA10008", "5Series", 2015), Row("shenzhen", "1AA100080", "9Series", 2015), Row("shenzhen", "1AA100081", "5Series", 2015), Row("wuhan", "1AA1000", "5Series", 2015), Row("wuhan", "1AA100000", "9Series", 2015), Row("wuhan", "1AA10002", "0Series", 2015), Row("wuhan", "1AA100033", "8Series", 2015), Row("wuhan", "1AA100053", "2Series", 2015), Row("wuhan", "1AA100056", "6Series", 2015), Row("wuhan", "1AA100067", "4Series", 2015), Row("wuhan", "1AA100074", "6Series", 2015), Row("wuhan", "1AA100076", "0Series", 2015), Row("xiangtan", "1AA100", "5Series", 2015), Row("xiangtan", "1AA100001", "0Series", 2015), Row("xiangtan", "1AA100012", "4Series", 2015), Row("xiangtan", "1AA100015", "4Series", 2015), Row("xiangtan", "1AA100017", "9Series", 2015), Row("xiangtan", "1AA100029", "2Series", 2015), Row("xiangtan", "1AA10003", "7Series", 2015), Row("xiangtan", "1AA100037", "7Series", 2015), Row("xiangtan", "1AA100045", "2Series", 2015), Row("xiangtan", "1AA10005", "8Series", 2015), Row("xiangtan", "1AA100060", "8Series", 2015), Row("xiangtan", "1AA100065", "0Series", 2015), Row("xiangtan", "1AA100069", "8Series", 2015), Row("xiangtan", "1AA10007", "8Series", 2015), Row("xiangtan", "1AA100079", "4Series", 2015), Row("xiangtan", "1AA100082", "5Series", 2015), Row("yichang", "1AA1", "7Series", 2015), Row("yichang", "1AA10", "7Series", 2015), Row("yichang", "1AA1000000", "7Series", 2015), Row("yichang", "1AA100004", "4Series", 2015), Row("yichang", "1AA100005", "1Series", 2015), Row("yichang", "1AA100009", "0Series", 2015), Row("yichang", "1AA100018", "8Series", 2015), Row("yichang", "1AA100026", "7Series", 2015), Row("yichang", "1AA100031", "7Series", 2015), Row("yichang", "1AA100040", "8Series", 2015), Row("yichang", "1AA100050", "2Series", 2015), Row("yichang", "1AA100055", "7Series", 2015), Row("yichang", "1AA100062", "9Series", 2015), Row("yichang", "1AA100063", "2Series", 2015), Row("yichang", "1AA100077", "3Series", 2015), Row("yichang", "1AA100078", "2Series", 2015), Row("zhuzhou", "1AA100003", "5Series", 2015), Row("zhuzhou", "1AA100010", "3Series", 2015), Row("zhuzhou", "1AA100014", "5Series", 2015), Row("zhuzhou", "1AA100019", "5Series", 2015), Row("zhuzhou", "1AA100022", "5Series", 2015), Row("zhuzhou", "1AA100027", "0Series", 2015), Row("zhuzhou", "1AA100028", "5Series", 2015), Row("zhuzhou", "1AA100030", "7Series", 2015), Row("zhuzhou", "1AA100047", "9Series", 2015), Row("zhuzhou", "1AA100052", "6Series", 2015), Row("zhuzhou", "1AA100057", "9Series", 2015), Row("zhuzhou", "1AA100064", "6Series", 2015), Row("zhuzhou", "1AA100066", "6Series", 2015), Row("zhuzhou", "1AA100073", "4Series", 2015), Row("zhuzhou", "1AA100083", "0Series", 2015)))
  })


  //TC_389
  test("SELECT imei, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei ORDER BY imei ASC")({
    checkAnswer(
      sql("SELECT imei, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei ORDER BY imei ASC"),
      Seq(Row("1AA1", 1), Row("1AA10", 10), Row("1AA100", 100), Row("1AA1000", 1000), Row("1AA10000", 10000), Row("1AA100000", 100000), Row("1AA1000000", 1000000), Row("1AA100001", 100001), Row("1AA100002", 100002), Row("1AA100003", 100003), Row("1AA100004", 100004), Row("1AA100005", 100005), Row("1AA100006", 100006), Row("1AA100007", 100007), Row("1AA100008", 100008), Row("1AA100009", 100009), Row("1AA10001", 10001), Row("1AA100010", 100010), Row("1AA100011", 100011), Row("1AA100012", 100012), Row("1AA100013", 100013), Row("1AA100014", 100014), Row("1AA100015", 100015), Row("1AA100016", 100016), Row("1AA100017", 100017), Row("1AA100018", 100018), Row("1AA100019", 100019), Row("1AA10002", 10002), Row("1AA100020", 100020), Row("1AA100021", 100021), Row("1AA100022", 100022), Row("1AA100023", 100023), Row("1AA100024", 100024), Row("1AA100025", 100025), Row("1AA100026", 100026), Row("1AA100027", 100027), Row("1AA100028", 100028), Row("1AA100029", 100029), Row("1AA10003", 10003), Row("1AA100030", 100030), Row("1AA100031", 100031), Row("1AA100032", 100032), Row("1AA100033", 100033), Row("1AA100034", 100034), Row("1AA100035", 100035), Row("1AA100036", 100036), Row("1AA100037", 100037), Row("1AA100038", 100038), Row("1AA100039", 100039), Row("1AA10004", 10004), Row("1AA100040", 100040), Row("1AA100041", 100041), Row("1AA100042", 100042), Row("1AA100043", 100043), Row("1AA100044", 100044), Row("1AA100045", 100045), Row("1AA100046", 100046), Row("1AA100047", 100047), Row("1AA100048", 100048), Row("1AA100049", 100049), Row("1AA10005", 10005), Row("1AA100050", 100050), Row("1AA100051", 100051), Row("1AA100052", 100052), Row("1AA100053", 100053), Row("1AA100054", 100054), Row("1AA100055", 100055), Row("1AA100056", 100056), Row("1AA100057", 100057), Row("1AA100058", 100058), Row("1AA100059", 100059), Row("1AA10006", 10006), Row("1AA100060", 100060), Row("1AA100061", 100061), Row("1AA100062", 100062), Row("1AA100063", 100063), Row("1AA100064", 100064), Row("1AA100065", 100065), Row("1AA100066", 100066), Row("1AA100067", 100067), Row("1AA100068", 100068), Row("1AA100069", 100069), Row("1AA10007", 10007), Row("1AA100070", 100070), Row("1AA100071", 100071), Row("1AA100072", 100072), Row("1AA100073", 100073), Row("1AA100074", 100074), Row("1AA100075", 100075), Row("1AA100076", 100076), Row("1AA100077", 100077), Row("1AA100078", 100078), Row("1AA100079", 100079), Row("1AA10008", 10008), Row("1AA100080", 100080), Row("1AA100081", 100081), Row("1AA100082", 100082), Row("1AA100083", 100083), Row("1AA100084", 100084)))
  })

  //TC_422
  test("select  min(channelsName) from Carbon_automation_test6 where  deviceinformationid is  null")({
    checkAnswer(
      sql("select  min(channelsName) from Carbon_automation_test6 where  deviceinformationid is  null"),
      Seq(Row("null")))
  })

  //TC_423
  test("select  max(channelsName) from  Carbon_automation_test6 where  deviceinformationid is  null")({
    checkAnswer(
      sql("select  max(channelsName) from  Carbon_automation_test6 where  deviceinformationid is  null"),
      Seq(Row("null")))
  })

  //TC_437
  test("SELECT sum(deviceInformationId) FROM Carbon_automation_test6 where imei is NOT null")({
    checkAnswer(
      sql("SELECT sum(deviceInformationId) FROM Carbon_automation_test6 where imei is NOT null"),
      Seq(Row(9594717)))
  })

  //TC_441
  test("select variance(gamepointid), var_pop(gamepointid)  from Carbon_automation_test6 where channelsid>2")({
    checkAnswer(
      sql("select variance(gamepointid), var_pop(gamepointid)  from Carbon_automation_test6 where channelsid>2"),
      Seq(Row(622630.4599570761, 622630.4599570761)))
  })

  //TC_445
  test("select variance(bomcode), var_pop(gamepointid)  from Carbon_automation_test6 where activeareaid>3")({
    checkAnswer(
      sql("select variance(bomcode), var_pop(gamepointid)  from Carbon_automation_test6 where activeareaid>3"),
      Seq(Row(1.4776446556169722E10, 663683.3954750763)))
  })

  //TC_447
  test("select var_samp(contractNumber) from Carbon_automation_test6")({
    checkAnswer(
      sql("select var_samp(contractNumber) from Carbon_automation_test6"),
      Seq(Row(8.247507095799066E12)))
  })

  //TC_464
  test("select covar_pop(gamePointId,deviceInformationId ) from Carbon_automation_test6")({
    checkAnswer(
      sql("select covar_pop(gamePointId,deviceInformationId ) from Carbon_automation_test6"),
      Seq(Row(-1057699.581875421)))
  })

  //TC_471
  test("select corr(Latest_MONTH, gamePointId) from Carbon_automation_test6")({
    checkAnswer(
      sql("select corr(Latest_MONTH, gamePointId) from Carbon_automation_test6"),
      Seq(Row("NaN")))
  })

  //TC_498
  test("select covar_pop(gamePointId, contractNumber) from Carbon_automation_test6")({
    checkAnswer(
      sql("select covar_pop(gamePointId, contractNumber) from Carbon_automation_test6"),
      Seq(Row(-1.3381444931641178E7)))
  })

  //TC_499
  test("select covar_samp(gamePointId, contractNumber) from Carbon_automation_test6")({
    checkAnswer(
      sql("select covar_samp(gamePointId, contractNumber) from Carbon_automation_test6"),
      Seq(Row(-1.3517990288086496E7)))
  })

//AllDataTypesTestCase3


  //TC_270
  test("SELECT AMSize, ActiveAreaId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE AMSize < \"0RAM size\" GROUP BY AMSize, ActiveAreaId ORDER BY AMSize ASC, ActiveAreaId ASC") ({
    checkAnswer(
      sql("SELECT AMSize, ActiveAreaId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE AMSize < \"0RAM size\" GROUP BY AMSize, ActiveAreaId ORDER BY AMSize ASC, ActiveAreaId ASC"),
      Seq())
  })

  //TC_112
  test("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test61") ({
    validateResult(
      sql("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test6"),
      "TC_112.csv")
  })

  //TC_115
  test("select percentile_approx(deviceInformationId,array(0.2,0.3,0.99))  as a from Carbon_automation_test6") ({
    validateResult(
      sql("select percentile_approx(deviceInformationId,array(0.2,0.3,0.99))  as a from Carbon_automation_test6"),
      "TC_115.csv")
  })

  //TC_116
  test("select percentile_approx(deviceInformationId,array(0.2,0.3,0.99),5) as a from Carbon_automation_test6") ({
    validateResult(
      sql("select percentile_approx(deviceInformationId,array(0.2,0.3,0.99),5) as a from Carbon_automation_test6"),
      "TC_116.csv")
  })

  //TC_117
  test("select histogram_numeric(deviceInformationId,2)  as a from Carbon_automation_test6") ({
    validateResult(
      sql("select histogram_numeric(deviceInformationId,2)  as a from Carbon_automation_test6"),
      "TC_117.csv")
  })

  //TC_128
  test("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test6") ({
    validateResult(
      sql("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test6"),
      "TC_128.csv")
  })

  //TC_131
  test("select percentile_approx(gamePointId,array(0.2,0.3,0.99))  as a from Carbon_automation_test6") ({
    validateResult(
      sql("select percentile_approx(gamePointId,array(0.2,0.3,0.99))  as a from Carbon_automation_test6"),
      "TC_131.csv")
  })

  //TC_132
  test("select percentile_approx(gamePointId,array(0.2,0.3,0.99),5) as a from Carbon_automation_test6") ({
    validateResult(
      sql("select percentile_approx(gamePointId,array(0.2,0.3,0.99),5) as a from Carbon_automation_test6"),
      "TC_132.csv")
  })

  //TC_133
  test("select histogram_numeric(gamePointId,2)  as a from Carbon_automation_test6") ({
    validateResult(
      sql("select histogram_numeric(gamePointId,2)  as a from Carbon_automation_test6"),
      "TC_133.csv")
  })

  //TC_477
  test("select percentile(1,array(1)) from Carbon_automation_test6") ({
    validateResult(
      sql("select percentile(1,array(1)) from Carbon_automation_test6"),
      "TC_477.csv")
  })

  //TC_479
  test("select percentile(1,array('0.5')) from Carbon_automation_test6") ({
    validateResult(
      sql("select percentile(1,array('0.5')) from Carbon_automation_test6"),
      "TC_479.csv")
  })

  //TC_480
  test("select percentile(1,array('1')) from Carbon_automation_test6") ({
    validateResult(
      sql("select percentile(1,array('1')) from Carbon_automation_test6"),
      "TC_480.csv")
  })

  //TC_485
  test("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test611") ({
    validateResult(
      sql("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test6"),
      "TC_485.csv")
  })

  //TC_486
  test("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test62") ({
    validateResult(
      sql("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test6"),
      "TC_486.csv")
  })

  //TC_487
  test("select histogram_numeric(1, 5000)from Carbon_automation_test6") ({
    validateResult(
      sql("select histogram_numeric(1, 5000)from Carbon_automation_test6"),
      "TC_487.csv")
  })

  //TC_488
  test("select histogram_numeric(1, 1000)from Carbon_automation_test6") ({
    validateResult(
      sql("select histogram_numeric(1, 1000)from Carbon_automation_test6"),
      "TC_488.csv")
  })

  //TC_489
  test("select histogram_numeric(1, 500)from Carbon_automation_test61") ({
    validateResult(
      sql("select histogram_numeric(1, 500)from Carbon_automation_test6"),
      "TC_489.csv")
  })

  //TC_490
  test("select histogram_numeric(1, 500)from Carbon_automation_test6") ({
    validateResult(
      sql("select histogram_numeric(1, 500)from Carbon_automation_test6"),
      "TC_490.csv")
  })

  //TC_491
  test("select collect_set(gamePointId) from Carbon_automation_test6") ({
    validateResult(
      sql("select collect_set(gamePointId) from Carbon_automation_test6"),
      "TC_491.csv")
  })

  //TC_492
  test("select collect_set(AMSIZE) from Carbon_automation_test61") ({
    validateResult(
      sql("select collect_set(AMSIZE) from Carbon_automation_test6"),
      "TC_492.csv")
  })

  //TC_493
  test("select collect_set(bomcode) from Carbon_automation_test6") ({
    validateResult(
      sql("select collect_set(bomcode) from Carbon_automation_test6"),
      "TC_493.csv")
  })

  //TC_494
  test("select collect_set(imei) from Carbon_automation_test6") ({
    validateResult(
      sql("select collect_set(imei) from Carbon_automation_test6"),
      "TC_494.csv")
  })

  //TC_500
  test("select percentile(1,array('0.5')) from Carbon_automation_test61") ({
    validateResult(
      sql("select percentile(1,array('0.5')) from Carbon_automation_test6"),
      "TC_500.csv")
  })

  //TC_501
  test("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test61") ({
    validateResult(
      sql("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test6"),
      "TC_501.csv")
  })

  //TC_502
  test("select collect_set(AMSIZE) from Carbon_automation_test6") ({
    validateResult(
      sql("select collect_set(AMSIZE) from Carbon_automation_test6"),
      "TC_502.csv")
  })

  //TC_612
  test("SELECT Carbon_automation_test6.gamePointId AS gamePointId,Carbon_automation_test6.AMSize AS AMSize, Carbon_automation_test6.ActiveCountry AS ActiveCountry, Carbon_automation_test6.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test6) SUB_QRY ) Carbon_automation_test6 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test6) SUB_QRY ) Carbon_automation_test61 ON Carbon_automation_test6.AMSize = Carbon_automation_test61.AMSize WHERE Carbon_automation_test6.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test6.AMSize, Carbon_automation_test6.ActiveCountry, Carbon_automation_test6.Activecity ,Carbon_automation_test6.gamePointId ORDER BY Carbon_automation_test6.AMSize ASC, Carbon_automation_test6.ActiveCountry ASC, Carbon_automation_test6.Activecity ASC") ({
    validateResult(sql("SELECT Carbon_automation_test6.gamePointId AS gamePointId,Carbon_automation_test6.AMSize AS AMSize, Carbon_automation_test6.ActiveCountry AS ActiveCountry, Carbon_automation_test6.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test6) SUB_QRY ) Carbon_automation_test6 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test6) SUB_QRY ) Carbon_automation_test61 ON Carbon_automation_test6.AMSize = Carbon_automation_test61.AMSize WHERE Carbon_automation_test6.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test6.AMSize, Carbon_automation_test6.ActiveCountry, Carbon_automation_test6.Activecity ,Carbon_automation_test6.gamePointId ORDER BY Carbon_automation_test6.AMSize ASC, Carbon_automation_test6.ActiveCountry ASC, Carbon_automation_test6.Activecity ASC"), "TC_612.csv")
  })


  //VMALL_Per_TC_000
  test("select count(*) from    myvmallTest") ({
    checkAnswer(
      sql("select count(*) from    myvmallTest"),
      Seq(Row(1003)))
  })

  //VMALL_Per_TC_001
  test("SELECT product_name, count(distinct imei) DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY product_name ORDER BY product_name ASC") ({
    validateResult(sql("SELECT product_name, count(distinct imei) DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY product_name ORDER BY product_name ASC"), "VMALL_Per_TC_001.csv")

  })

  //VMALL_Per_TC_002
  test("SELECT device_name, product, product_name, COUNT(DISTINCT imei) AS DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY device_name, product, product_name ORDER BY device_name ASC, product ASC, product_name ASC") ({
    validateResult(sql("SELECT device_name, product, product_name, COUNT(DISTINCT imei) AS DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY device_name, product, product_name ORDER BY device_name ASC, product ASC, product_name ASC"), "VMALL_Per_TC_002.csv")
  })

  //VMALL_Per_TC_004
  test("SELECT device_color FROM (select * from myvmallTest) SUB_QRY GROUP BY device_color ORDER BY device_color ASC") ({
    validateResult(sql("SELECT device_color FROM (select * from myvmallTest) SUB_QRY GROUP BY device_color ORDER BY device_color ASC"), "VMALL_Per_TC_004.csv")
  })

  //VMALL_Per_TC_005
  test("SELECT product_name  FROM (select * from myvmallTest) SUB_QRY GROUP BY product_name ORDER BY  product_name ASC") ({
    validateResult(sql("SELECT product_name  FROM (select * from myvmallTest) SUB_QRY GROUP BY product_name ORDER BY  product_name ASC"), "VMALL_Per_TC_005.csv")
  })

  //VMALL_Per_TC_006
  test("SELECT product, COUNT(DISTINCT packing_list_no) AS LONG_COL_0 FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC") ({
    validateResult(sql("SELECT product, COUNT(DISTINCT packing_list_no) AS LONG_COL_0 FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC"), "VMALL_Per_TC_006.csv")
  })

  //VMALL_Per_TC_007
  test("select count(distinct imei) DistinctCount_imei from myvmallTest") ({
    checkAnswer(
      sql("select count(distinct imei) DistinctCount_imei from myvmallTest"),
      Seq(Row(1001)))
  })

  //VMALL_Per_TC_008
  test("Select count(imei),deliveryCountry  from myvmallTest group by deliveryCountry order by deliveryCountry asc") ({
    validateResult(sql("Select count(imei),deliveryCountry  from myvmallTest group by deliveryCountry order by deliveryCountry asc"), "VMALL_Per_TC_008.csv")
  })

  //VMALL_Per_TC_009
  test("select (t1.hnor6emui/t2.totalc)*100 from (select count (Active_emui_version)  as hnor6emui from myvmallTest where Active_emui_version=\"EmotionUI_2.1\")t1,(select count(Active_emui_version) as totalc from myvmallTest)t2") ({
    checkAnswer(
      sql("select (t1.hnor6emui/t2.totalc)*100 from (select count (Active_emui_version)  as hnor6emui from myvmallTest where Active_emui_version=\"EmotionUI_2.1\")t1,(select count(Active_emui_version) as totalc from myvmallTest)t2"),
      Seq(Row(0.09970089730807577)))
  })

  //VMALL_Per_TC_012
  test("SELECT Active_os_version, count(distinct imei) DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_os_version ORDER BY Active_os_version ASC") ({
    validateResult(sql("SELECT Active_os_version, count(distinct imei) DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_os_version ORDER BY Active_os_version ASC"), "VMALL_Per_TC_012.csv")
  })

  //VMALL_Per_TC_B015
  test("SELECT product, count(distinct imei) DistinctCount_imei FROM myvmallTest GROUP BY product ORDER BY product ASC") ({
    validateResult(sql("SELECT product, count(distinct imei) DistinctCount_imei FROM myvmallTest GROUP BY product ORDER BY product ASC"), "VMALL_Per_TC_B015.csv")
  })

  //VMALL_Per_TC_B016
  test("SELECT Active_emui_version, product, product_desc, COUNT(DISTINCT imei) AS DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_emui_version, product, product_desc ORDER BY Active_emui_version ASC, product ASC, product_desc ASC") ({
    validateResult(sql("SELECT Active_emui_version, product, product_desc, COUNT(DISTINCT imei) AS DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_emui_version, product, product_desc ORDER BY Active_emui_version ASC, product ASC, product_desc ASC"), "VMALL_Per_TC_B016.csv")
  })

  //VMALL_Per_TC_B018
  test("SELECT Active_emui_version FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_emui_version ORDER BY Active_emui_version ASC") ({
    validateResult(sql("SELECT Active_emui_version FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_emui_version ORDER BY Active_emui_version ASC"), "VMALL_Per_TC_B018.csv")
  })

  //VMALL_Per_TC_B019
  test("SELECT product FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC") ({
    validateResult(sql("SELECT product FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC"), "VMALL_Per_TC_B019.csv")
  })

  //VMALL_Per_TC_B020
  test("SELECT product, COUNT(DISTINCT Active_emui_version) AS LONG_COL_0 FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC") ({
    validateResult(sql("SELECT product, COUNT(DISTINCT Active_emui_version) AS LONG_COL_0 FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC"), "VMALL_Per_TC_B020.csv")
  })

  //VMALL_Per_TC_023
  test("SELECT  count(imei) as distinct_imei,series FROM (select * from    myvmallTest   ) SUB_QRY where series LIKE 'series1%' group by series") ({
    validateResult(sql("SELECT  count(imei) as distinct_imei,series FROM (select * from    myvmallTest   ) SUB_QRY where series LIKE 'series1%' group by series"), "VMALL_Per_TC_023.csv")
  })

  //VMALL_Per_TC_038
  test("select Latest_network, count(distinct imei) as imei_number from  myvmallTest  group by Latest_network") ({
    validateResult(sql("select Latest_network, count(distinct imei) as imei_number from  myvmallTest  group by Latest_network"), "VMALL_Per_TC_038.csv")
  })

  //VMALL_Per_TC_039
  test("select device_name, count(distinct imei) as imei_number from  myvmallTest  group by device_name") ({
    validateResult(sql("select device_name, count(distinct imei) as imei_number from  myvmallTest  group by device_name"), "VMALL_Per_TC_039.csv")
  })


  //VMALL_Per_TC_040
  test("select product_name, count(distinct imei) as imei_number from  myvmallTest  group by product_name") ({
    validateResult(sql("select product_name, count(distinct imei) as imei_number from  myvmallTest  group by product_name"), "VMALL_Per_TC_040.csv")
  })

  //VMALL_Per_TC_041
  test("select deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity") ({
    validateResult(sql("select deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity"), "VMALL_Per_TC_041.csv")
  })

  //VMALL_Per_TC_042
  test("select device_name, deliverycity,count(distinct imei) as imei_number from  myvmallTest  group by device_name,deliverycity") ({
    validateResult(sql("select device_name, deliverycity,count(distinct imei) as imei_number from  myvmallTest  group by device_name,deliverycity"), "VMALL_Per_TC_042.csv")
  })

  //VMALL_Per_TC_043
  test("select product_name, device_name, count(distinct imei) as imei_number from  myvmallTest  group by product_name,device_name") ({
    validateResult(sql("select product_name, device_name, count(distinct imei) as imei_number from  myvmallTest  group by product_name,device_name"), "VMALL_Per_TC_043.csv")
  })

  //VMALL_Per_TC_044
  test("select product_name,deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity,product_name1") ({
    validateResult(sql("select product_name,deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity,product_name"), "VMALL_Per_TC_044.csv")
  })

  //VMALL_Per_TC_045
  test("select product_name,deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity,product_name") ({
    validateResult(sql("select product_name,deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity,product_name"), "VMALL_Per_TC_045.csv")
  })

  //VMALL_Per_TC_046
  test("select check_day,check_hour, count(distinct imei) as imei_number from  myvmallTest  group by check_day,check_hour") ({
    checkAnswer(
      sql("select check_day,check_hour, count(distinct imei) as imei_number from  myvmallTest  group by check_day,check_hour"),
      Seq(Row(15,-1,1000),Row(null,null,1)))
  })

  //VMALL_Per_TC_047
  test("select device_color,product_name, count(distinct imei) as imei_number from  myvmallTest  group by device_color,product_name order by product_name limit 1000") ({
    validateResult(sql("select device_color,product_name, count(distinct imei) as imei_number from  myvmallTest  group by device_color,product_name order by product_name limit 1000"), "VMALL_Per_TC_047.csv")
  })

  //VMALL_Per_TC_048
  test("select packing_hour,deliveryCity,device_color,count(distinct imei) as imei_number from  myvmallTest  group by packing_hour,deliveryCity,device_color order by deliveryCity  limit 1000") ({
    validateResult(sql("select packing_hour,deliveryCity,device_color,count(distinct imei) as imei_number from  myvmallTest  group by packing_hour,deliveryCity,device_color order by deliveryCity  limit 1000"), "VMALL_Per_TC_048.csv")
  })

  //VMALL_Per_TC_049
  test("SELECT product_name, count(distinct imei) DistinctCount_imei FROM  myvmallTest  GROUP BY product_name ORDER BY product_name ASC") ({
    validateResult(sql("SELECT product_name, count(distinct imei) DistinctCount_imei FROM  myvmallTest  GROUP BY product_name ORDER BY product_name ASC"), "VMALL_Per_TC_049.csv")
  })

  //VMALL_Per_TC_051
  test("SELECT device_color, product_name, COUNT(DISTINCT imei) AS DistinctCount_imei FROM  myvmallTest  GROUP BY device_color, product_name ORDER BY device_color ASC, product_name ASC") ({
    validateResult(sql("SELECT device_color, product_name, COUNT(DISTINCT imei) AS DistinctCount_imei FROM  myvmallTest  GROUP BY device_color, product_name ORDER BY device_color ASC, product_name ASC"), "VMALL_Per_TC_051.csv")
  })

  //VMALL_Per_TC_053
  test("SELECT product_name FROM  myvmallTest  SUB_QRY GROUP BY product_name ORDER BY product_name ASC") ({
    validateResult(sql("SELECT product_name FROM  myvmallTest  SUB_QRY GROUP BY product_name ORDER BY product_name ASC"), "VMALL_Per_TC_053.csv")
  })

  //VMALL_Per_TC_054
  test("SELECT product_name, COUNT(DISTINCT Active_emui_version) AS LONG_COL_0 FROM  myvmallTest  GROUP BY product_name ORDER BY product_name ASC") ({
    validateResult(sql("SELECT product_name, COUNT(DISTINCT Active_emui_version) AS LONG_COL_0 FROM  myvmallTest  GROUP BY product_name ORDER BY product_name ASC"), "VMALL_Per_TC_054.csv")
  })

  //SmartPCC_Perf_TC_002
  test("select MSISDN,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where RAT_NAME='GERAN' group by MSISDN having total < 1073741824 order by total desc")({
    checkAnswer(
      sql("select MSISDN,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where RAT_NAME='GERAN' group by MSISDN having total < 1073741824 order by total desc"),
      Seq(Row("8613893462639", 2874640), Row("8613993676885", 73783), Row("8618394185970", 23865), Row("8618793100458", 15112), Row("8618794812876", 14411), Row("8615120474362", 6936), Row("8613893853351", 6486), Row("8618393012284", 5700), Row("8613993800024", 5044), Row("8618794965341", 4840), Row("8613993899110", 4364), Row("8613519003078", 2485), Row("8613649905753", 2381), Row("8613893600602", 2346), Row("8615117035070", 1310), Row("8618700943475", 1185), Row("8613919791668", 928), Row("8615209309657", 290), Row("8613993104233", 280)))
  })


  //SmartPCC_Perf_TC_004
  test("select APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number, sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_CATEGORY_NAME")({
    checkAnswer(
      sql("select APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number, sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_CATEGORY_NAME"),
      Seq(Row("Network_Admin", 5, 12402), Row("Web_Browsing", 6, 2972886), Row("IM", 2, 29565), Row("Tunnelling", 1, 4364), Row("Game", 1, 2485), Row("", 4, 24684)))
  })

  //SmartPCC_Perf_TC_005
  test("select APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where APP_CATEGORY_NAME='Web_Browsing' group by APP_CATEGORY_NAME order by msidn_number desc")({
    checkAnswer(
      sql("select APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where APP_CATEGORY_NAME='Web_Browsing' group by APP_CATEGORY_NAME order by msidn_number desc"),
      Seq(Row("Web_Browsing", 6, 2972886)))
  })

  //SmartPCC_Perf_TC_006
  test("select APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME")({
    checkAnswer(
      sql("select APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME"),
      Seq(Row("QQ_Media", 1, 5700), Row("DNS", 5, 12402), Row("QQ_IM", 1, 23865), Row("HTTP", 4, 2896722), Row("XiaYiDao", 1, 2485), Row("HTTP_Browsing", 1, 2381), Row("HTTPS", 1, 73783), Row("", 4, 24684), Row("SSL", 1, 4364)))
  })

  //SmartPCC_Perf_TC_008
  test("select TERMINAL_BRAND,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by TERMINAL_BRAND")({
    checkAnswer(
      sql("select TERMINAL_BRAND,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by TERMINAL_BRAND"),
      Seq(Row("OPPO", 1, 928), Row("HTC", 2, 2661), Row("美奇", 1, 2485), Row("NOKIA", 1, 14411), Row("MARCONI", 1, 2874640), Row("SUNUP", 1, 290), Row("TIANYU", 1, 23865), Row("LANBOXING", 1, 4364), Row("BBK", 1, 6936), Row("SECURE", 1, 1185), Row("MOTOROLA", 3, 80137), Row("DAXIAN", 1, 6486), Row("LENOVO", 1, 2346), Row("", 1, 4840), Row("山水", 1, 5700), Row("SANGFEI", 1, 15112)))
  })


  //SmartPCC_Perf_TC_010
  test("select TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by TERMINAL_TYPE")({
    checkAnswer(
      sql("select TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by TERMINAL_TYPE"),
      Seq(Row(" ", 2, 2875825), Row("SMARTPHONE", 8, 123420), Row("", 1, 4840), Row("FEATURE PHONE", 8, 42301)))
  })

  //SmartPCC_Perf_TC_011
  test("select TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where RAT_NAME='GERAN' group by TERMINAL_TYPE order by total desc")({
    checkAnswer(
      sql("select TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where RAT_NAME='GERAN' group by TERMINAL_TYPE order by total desc"),
      Seq(Row(" ", 2, 2875825), Row("SMARTPHONE", 8, 123420), Row("FEATURE PHONE", 8, 42301), Row("", 1, 4840)))
  })

  //SmartPCC_Perf_TC_012
  test("select CGI,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by CGI")({
    checkAnswer(
      sql("select CGI,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by CGI"),
      Seq(Row("460003772902063", 1, 73783), Row("460003788311323", 1, 5700), Row("460003777109392", 1, 6486), Row("460003787211338", 1, 1310), Row("460003776440020", 1, 5044), Row("460003773401611", 1, 2381), Row("460003767804016", 1, 4840), Row("460003784806621", 1, 1185), Row("460003787360026", 1, 14411), Row("460003785041401", 1, 6936), Row("460003766033446", 1, 15112), Row("460003776906411", 1, 4364), Row("460003782800719", 1, 2874640), Row("460003764930757", 1, 928), Row("460003788410098", 1, 23865), Row("460003763202233", 1, 2485), Row("460003763606253", 1, 290), Row("460003788100762", 1, 280), Row("460003784118872", 1, 2346)))
  })

  //SmartPCC_Perf_TC_014
  test("select RAT_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by RAT_NAME")({
    checkAnswer(
      sql("select RAT_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by RAT_NAME"),
      Seq(Row("GERAN", 19, 3046386)))
  })

  //SmartPCC_Perf_TC_015
  test("select DAY,HOUR,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by DAY,HOUR")({
    checkAnswer(
      sql("select DAY,HOUR,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by DAY,HOUR"),
      Seq(Row("8-1", "23", 19, 3046386)))
  })

  //SmartPCC_Perf_TC_016
  test("select DAY,HOUR,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where hour between 20 and 24 group by DAY,HOUR order by total desc")({
    checkAnswer(
      sql("select DAY,HOUR,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where hour between 20 and 24 group by DAY,HOUR order by total desc"),
      Seq(Row("8-1", "23", 19, 3046386)))
  })

  //SmartPCC_Perf_TC_017
  test("select APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME")({
    checkAnswer(
      sql("select APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME"),
      Seq(Row("HTTPS", "Web_Browsing", 1, 73783), Row("QQ_IM", "IM", 1, 23865), Row("HTTP_Browsing", "Web_Browsing", 1, 2381), Row("XiaYiDao", "Game", 1, 2485), Row("", "", 4, 24684), Row("SSL", "Tunnelling", 1, 4364), Row("HTTP", "Web_Browsing", 4, 2896722), Row("QQ_Media", "IM", 1, 5700), Row("DNS", "Network_Admin", 5, 12402)))
  })

  //SmartPCC_Perf_TC_018
  test("select APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where  APP_CATEGORY_NAME='Web_Browsing' group by APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME order by total desc")({
    checkAnswer(
      sql("select APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where  APP_CATEGORY_NAME='Web_Browsing' group by APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME order by total desc"),
      Seq(Row("HTTP", "Web_Browsing", 4, 2896722), Row("HTTPS", "Web_Browsing", 1, 73783), Row("HTTP_Browsing", "Web_Browsing", 1, 2381)))
  })

  //SmartPCC_Perf_TC_019
  test("select TERMINAL_BRAND,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME,TERMINAL_BRAND")({
    checkAnswer(
      sql("select TERMINAL_BRAND,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME,TERMINAL_BRAND"),
      Seq(Row("SECURE", "HTTP", 1, 1185), Row("HTC", "HTTP_Browsing", 1, 2381), Row("TIANYU", "QQ_IM", 1, 23865), Row("DAXIAN", "HTTP", 1, 6486), Row("BBK", "", 1, 6936), Row("山水", "QQ_Media", 1, 5700), Row("LENOVO", "", 1, 2346), Row("LANBOXING", "SSL", 1, 4364), Row("MOTOROLA", "DNS", 2, 6354), Row("MOTOROLA", "HTTPS", 1, 73783), Row("SANGFEI", "", 1, 15112), Row("美奇", "XiaYiDao", 1, 2485), Row("NOKIA", "HTTP", 1, 14411), Row("", "DNS", 1, 4840), Row("MARCONI", "HTTP", 1, 2874640), Row("OPPO", "DNS", 1, 928), Row("HTC", "DNS", 1, 280), Row("SUNUP", "", 1, 290)))
  })

  //SmartPCC_Perf_TC_021
  test("select RAT_NAME,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g   group by APP_SUB_CATEGORY_NAME,RAT_NAME")({
    checkAnswer(
      sql("select RAT_NAME,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g   group by APP_SUB_CATEGORY_NAME,RAT_NAME"),
      Seq(Row("GERAN", "QQ_Media", 1, 5700), Row("GERAN", "HTTP", 4, 2896722), Row("GERAN", "XiaYiDao", 1, 2485), Row("GERAN", "", 4, 24684), Row("GERAN", "QQ_IM", 1, 23865), Row("GERAN", "DNS", 5, 12402), Row("GERAN", "HTTPS", 1, 73783), Row("GERAN", "SSL", 1, 4364), Row("GERAN", "HTTP_Browsing", 1, 2381)))
  })

  //SmartPCC_Perf_TC_022
  test("select RAT_NAME,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where  RAT_NAME='GERAN' group by APP_SUB_CATEGORY_NAME,RAT_NAME order by total desc")({
    checkAnswer(
      sql("select RAT_NAME,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where  RAT_NAME='GERAN' group by APP_SUB_CATEGORY_NAME,RAT_NAME order by total desc"),
      Seq(Row("GERAN", "HTTP", 4, 2896722), Row("GERAN", "HTTPS", 1, 73783), Row("GERAN", "", 4, 24684), Row("GERAN", "QQ_IM", 1, 23865), Row("GERAN", "DNS", 5, 12402), Row("GERAN", "QQ_Media", 1, 5700), Row("GERAN", "SSL", 1, 4364), Row("GERAN", "XiaYiDao", 1, 2485), Row("GERAN", "HTTP_Browsing", 1, 2381)))
  })

  //SmartPCC_Perf_TC_023
  test("select TERMINAL_TYPE,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g   group by APP_SUB_CATEGORY_NAME,TERMINAL_TYPE")({
    checkAnswer(
      sql("select TERMINAL_TYPE,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g   group by APP_SUB_CATEGORY_NAME,TERMINAL_TYPE"),
      Seq(Row("SMARTPHONE", "", 1, 2346), Row("SMARTPHONE", "QQ_IM", 1, 23865), Row("FEATURE PHONE", "QQ_Media", 1, 5700), Row("SMARTPHONE", "DNS", 3, 6634), Row("FEATURE PHONE", "HTTP", 1, 6486), Row("SMARTPHONE", "HTTPS", 1, 73783), Row("FEATURE PHONE", "XiaYiDao", 1, 2485), Row("SMARTPHONE", "HTTP_Browsing", 1, 2381), Row(" ", "HTTP", 2, 2875825), Row("FEATURE PHONE", "", 3, 22338), Row("", "DNS", 1, 4840), Row("FEATURE PHONE", "DNS", 1, 928), Row("FEATURE PHONE", "SSL", 1, 4364), Row("SMARTPHONE", "HTTP", 1, 14411)))
  })

  //SmartPCC_Perf_TC_025
  test("select HOUR,cgi,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT+down_THROUGHPUT) as total from  traffic_2g_3g_4g  group by HOUR,cgi,APP_SUB_CATEGORY_NAME")({
    checkAnswer(
      sql("select HOUR,cgi,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT+down_THROUGHPUT) as total from  traffic_2g_3g_4g  group by HOUR,cgi,APP_SUB_CATEGORY_NAME"),
      Seq(Row("23", "460003788311323", "QQ_Media", 1, 5700), Row("23", "460003763606253", "", 1, 290), Row("23", "460003784806621", "HTTP", 1, 1185), Row("23", "460003776440020", "DNS", 1, 5044), Row("23", "460003772902063", "HTTPS", 1, 73783), Row("23", "460003782800719", "HTTP", 1, 2874640), Row("23", "460003776906411", "SSL", 1, 4364), Row("23", "460003788410098", "QQ_IM", 1, 23865), Row("23", "460003766033446", "", 1, 15112), Row("23", "460003763202233", "XiaYiDao", 1, 2485), Row("23", "460003764930757", "DNS", 1, 928), Row("23", "460003787211338", "DNS", 1, 1310), Row("23", "460003767804016", "DNS", 1, 4840), Row("23", "460003773401611", "HTTP_Browsing", 1, 2381), Row("23", "460003784118872", "", 1, 2346), Row("23", "460003785041401", "", 1, 6936), Row("23", "460003777109392", "HTTP", 1, 6486), Row("23", "460003788100762", "DNS", 1, 280), Row("23", "460003787360026", "HTTP", 1, 14411)))
  })

  //SmartPCC_Perf_TC_026
  test("select RAT_NAME,APP_SUB_CATEGORY_NAME,TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by RAT_NAME,APP_SUB_CATEGORY_NAME,TERMINAL_TYPE")({
    checkAnswer(
      sql("select RAT_NAME,APP_SUB_CATEGORY_NAME,TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by RAT_NAME,APP_SUB_CATEGORY_NAME,TERMINAL_TYPE"),
      Seq(Row("GERAN", "SSL", "FEATURE PHONE", 1, 4364), Row("GERAN", "HTTP", "SMARTPHONE", 1, 14411), Row("GERAN", "", "SMARTPHONE", 1, 2346), Row("GERAN", "QQ_IM", "SMARTPHONE", 1, 23865), Row("GERAN", "QQ_Media", "FEATURE PHONE", 1, 5700), Row("GERAN", "DNS", "SMARTPHONE", 3, 6634), Row("GERAN", "HTTPS", "SMARTPHONE", 1, 73783), Row("GERAN", "HTTP", "FEATURE PHONE", 1, 6486), Row("GERAN", "XiaYiDao", "FEATURE PHONE", 1, 2485), Row("GERAN", "HTTP_Browsing", "SMARTPHONE", 1, 2381), Row("GERAN", "HTTP", " ", 2, 2875825), Row("GERAN", "", "FEATURE PHONE", 3, 22338), Row("GERAN", "DNS", "", 1, 4840), Row("GERAN", "DNS", "FEATURE PHONE", 1, 928)))
  })

  //SmartPCC_Perf_TC_027
  test("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN")({
    checkAnswer(
      sql("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN"),
      Seq(Row("8613993676885", "HTTPS", 1, 73783), Row("8618394185970", "QQ_IM", 1, 23865), Row("8613993800024", "DNS", 1, 5044), Row("8613893600602", "", 1, 2346), Row("8613919791668", "DNS", 1, 928), Row("8618793100458", "", 1, 15112), Row("8618794812876", "HTTP", 1, 14411), Row("8618700943475", "HTTP", 1, 1185), Row("8613993104233", "DNS", 1, 280), Row("8615120474362", "", 1, 6936), Row("8615209309657", "", 1, 290), Row("8613893462639", "HTTP", 1, 2874640), Row("8615117035070", "DNS", 1, 1310), Row("8613519003078", "XiaYiDao", 1, 2485), Row("8613893853351", "HTTP", 1, 6486), Row("8613649905753", "HTTP_Browsing", 1, 2381), Row("8618794965341", "DNS", 1, 4840), Row("8613993899110", "SSL", 1, 4364), Row("8618393012284", "QQ_Media", 1, 5700)))
  })

  //SmartPCC_Perf_TC_028
  test("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.TERMINAL_BRAND='HTC' and t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN")({
    checkAnswer(
      sql("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.TERMINAL_BRAND='HTC' and t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN"),
      Seq(Row("8613993104233", "DNS", 1, 280), Row("8613649905753", "HTTP_Browsing", 1, 2381)))
  })

  //SmartPCC_Perf_TC_029
  test("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.TERMINAL_BRAND='HTC' and t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN order by total desc")({
    checkAnswer(
      sql("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.TERMINAL_BRAND='HTC' and t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN order by total desc"),
      Seq(Row("8613649905753", "HTTP_Browsing", 1, 2381), Row("8613993104233", "DNS", 1, 280)))
  })

  //SmartPCC_Perf_TC_030
  test("select t2.MSISDN,t1.RAT_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.MSISDN=t2.MSISDN group by t1.RAT_NAME,t2.MSISDN")({
    checkAnswer(
      sql("select t2.MSISDN,t1.RAT_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.MSISDN=t2.MSISDN group by t1.RAT_NAME,t2.MSISDN"),
      Seq(Row("8618794965341", "GERAN", 1, 4840), Row("8613993676885", "GERAN", 1, 73783), Row("8613893462639", "GERAN", 1, 2874640), Row("8613993800024", "GERAN", 1, 5044), Row("8618394185970", "GERAN", 1, 23865), Row("8613893853351", "GERAN", 1, 6486), Row("8613919791668", "GERAN", 1, 928), Row("8613993104233", "GERAN", 1, 280), Row("8613893600602", "GERAN", 1, 2346), Row("8618393012284", "GERAN", 1, 5700), Row("8613519003078", "GERAN", 1, 2485), Row("8618793100458", "GERAN", 1, 15112), Row("8615117035070", "GERAN", 1, 1310), Row("8615120474362", "GERAN", 1, 6936), Row("8613649905753", "GERAN", 1, 2381), Row("8615209309657", "GERAN", 1, 290), Row("8613993899110", "GERAN", 1, 4364), Row("8618794812876", "GERAN", 1, 14411), Row("8618700943475", "GERAN", 1, 1185)))
  })

  //SmartPCC_Perf_TC_031
  test("select level, sum(sumUPdown) as total, count(distinct MSISDN) as MSISDN_count from (select MSISDN, t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT as sumUPdown, if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>52428800, '>50M', if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>10485760,'50M~10M',if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>1048576, '10M~1M','<1m'))) as level from  traffic_2g_3g_4g  t1) t2 group by level")({
    checkAnswer(
      sql("select level, sum(sumUPdown) as total, count(distinct MSISDN) as MSISDN_count from (select MSISDN, t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT as sumUPdown, if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>52428800, '>50M', if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>10485760,'50M~10M',if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>1048576, '10M~1M','<1m'))) as level from  traffic_2g_3g_4g  t1) t2 group by level"),
      Seq(Row("<1m", 171746, 18), Row("10M~1M", 2874640, 1)))
  })

  //SmartPCC_Perf_TC_036
  test("select SOURCE_INFO,APP_CATEGORY_ID,APP_CATEGORY_NAME,AREA_CODE,CITY,UP_THROUGHPUT,DOWN_THROUGHPUT from Traffic_2G_3G_4G where MSISDN='8615209309657' and APP_CATEGORY_ID='-1'")({
    checkAnswer(
      sql("select SOURCE_INFO,APP_CATEGORY_ID,APP_CATEGORY_NAME,AREA_CODE,CITY,UP_THROUGHPUT,DOWN_THROUGHPUT from Traffic_2G_3G_4G where MSISDN='8615209309657' and APP_CATEGORY_ID='-1'"),
      Seq(Row("GN", "-1", "", "0930", "临夏", 200, 90)))
  })


  //AllDataTypesTestcase4


  //TC_1063
  test("TC_1063") {
    sql("CREATE CUBE cube5 DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [col2 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1) ,PARTITION_COUNT=1] )")
    sql("drop cube cube5")
  }

  //TC_1064
  test("TC_1064") {
    sql("CREATE CUBE myschema.cube6 DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [col2 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1) ,PARTITION_COUNT=1] )")
    sql("drop cube myschema.cube6")
  }

  //TC_1067
  test("TC_1067") {
    sql("CREATE CUBE cube9 DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [Latest_Day = count])")
    sql("drop cube cube9")
  }

  //TC_1068
  test("TC_1068") {
    sql("CREATE CUBE myschema.cube10 DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [Latest_Day = count])")
    sql("drop cube myschema.cube10")
  }

  //TC_1071
  test("TC_1071") {
    sql("CREATE CUBE myschema.cube29 DIMENSIONS (AMSize STRING as col1) OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1) ,PARTITION_COUNT=1] )")
    sql("drop cube myschema.cube29")
  }

  //TC_1072
  test("TC_1072") {
    sql("CREATE CUBE cube30 DIMENSIONS (AMSize STRING as col1) OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1) ,PARTITION_COUNT=1] )")
    sql("drop cube cube30")
  }

  //TC_1078
  test("TC_1078") {
    sql("CREATE CUBE cube36 DIMENSIONS (AMSize STRING as col1,deviceInformationId STRING as col2) MEASURES (Latest_Day INTEGER as col3) OPTIONS (AGGREGATION [col3 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1,col2) ,PARTITION_COUNT=1] )")
    sql("drop cube cube36")
  }

  //TC_1079
  test("TC_1079") {
    sql("CREATE CUBE myschema.cube37 DIMENSIONS (AMSize STRING as col1,deviceInformationId STRING as col2) MEASURES (Latest_Day INTEGER as col3) OPTIONS (AGGREGATION [col3 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1,col2) ,PARTITION_COUNT=1] )")
    sql("drop cube myschema.cube37")
  }

  //TC_1084
  test("TC_1084") {
    sql("CREATE CUBE myschema.cube42 DIMENSIONS (bomCode integer as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [col2 = count])")
    sql("drop cube myschema.cube42")
  }

  //TC_1085
  test("TC_1085") {
    sql("CREATE CUBE cube43 DIMENSIONS (bomCode integer as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [col2 = count])")
    sql("drop cube cube43")
  }

  //TC_1089
  test("TC_1089") {
    sql("CREATE CUBE myschema.cube47 DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day numeric as col2) OPTIONS (AGGREGATION [bomCode = count])")
    sql("drop cube myschema.cube47")
  }

  //TC_1090
  test("TC_1090") {
    sql("CREATE CUBE cube48 DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day numeric as col2) OPTIONS (AGGREGATION [bomCode = count])")
    sql("drop cube cube48")
  }

  //TC_1094
  test("TC_1094") {
    sql("CREATE CUBE myschema.cube52 DIMENSIONS (AMSize numeric as col1) MEASURES (Latest_Day numeric as col2) OPTIONS (AGGREGATION [Latest_Day = count])")
    sql("drop cube myschema.cube52")
  }

  //TC_1095
  test("TC_1095") {
    sql("CREATE CUBE cube53 DIMENSIONS (col1 numeric as col1) MEASURES (Latest_Day numeric as col2) OPTIONS (AGGREGATION [Latest_Day = count])")
    sql("drop cube cube53")
  }

  //DTS2015102804520
  test("DTS2015102804520") {
    sql("CREATE CUBE default.cube431 DIMENSIONS (bomCode integer as col1) MEASURES (Latest_Day INTEGER as col2)")
    sql("drop cube default.cube431")
  }

  //TC_1118
  test("TC_1118") {
    sql("CREATE CUBE cube5_drop DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [col2 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1) ,PARTITION_COUNT=1] )")
    sql("drop cube cube5_drop")
  }

  //TC_1119
  test("TC_1119") {
    sql("CREATE CUBE myschema.cube6_drop DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [col2 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1) ,PARTITION_COUNT=1] )")
    sql("drop cube myschema.cube6_drop")
  }


  //TC_1122
  test("TC_1122") {
    sql("CREATE CUBE cube9_drop DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [Latest_Day = count])")
    sql("drop cube cube9_drop")
  }

  //TC_1123
  test("TC_1123") {
    sql("CREATE CUBE myschema.cube10_drop DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day INTEGER as col2) OPTIONS (AGGREGATION [Latest_Day = count])")
    sql("drop cube myschema.cube10_drop")
  }


  //TC_1126
  test("TC_1126") {
    sql("CREATE CUBE myschema.cube29_drop DIMENSIONS (AMSize STRING as col1) OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1) ,PARTITION_COUNT=1] )")
    sql("drop cube myschema.cube29_drop")
  }

  //TC_1127
  test("TC_1127") {
    sql("CREATE CUBE cube30_drop DIMENSIONS (AMSize STRING as col1) OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1) ,PARTITION_COUNT=1] )")
    sql("drop cube cube30_drop")
  }
  //TC_1131
  test("TC_1131") {
    sql("CREATE CUBE cube36_drop DIMENSIONS (AMSize STRING as col1,deviceInformationId STRING as col2) MEASURES (Latest_Day INTEGER as col3) OPTIONS (AGGREGATION [col3 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1,col2) ,PARTITION_COUNT=1] )")
    sql("drop cube cube36_drop")
  }

  //TC_1132
  test("TC_1132") {
    sql("CREATE CUBE myschema.cube37_drop DIMENSIONS (AMSize STRING as col1,deviceInformationId STRING as col2) MEASURES (Latest_Day INTEGER as col3) OPTIONS (AGGREGATION [col3 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (col1,col2) ,PARTITION_COUNT=1] )")
    sql("drop cube myschema.cube37_drop")
  }

  //TC_1137
  test("TC_1137") {
    sql("CREATE CUBE myschema.cube47_drop DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day numeric as col2) OPTIONS (AGGREGATION [bomCode = count])")
    sql("drop cube myschema.cube47_drop")
  }

  //TC_1138
  test("TC_1138") {
    sql("CREATE CUBE cube48_drop DIMENSIONS (AMSize STRING as col1) MEASURES (Latest_Day numeric as col2) OPTIONS (AGGREGATION [bomCode = count])")
    sql("drop cube cube48_drop")
  }

  //TC_1141
  test("TC_1141") {
    sql("CREATE CUBE myschema.cube52_drop DIMENSIONS (AMSize numeric as col1) MEASURES (Latest_Day numeric as col2) OPTIONS (AGGREGATION [Latest_Day = count])")
    sql("drop cube myschema.cube52_drop")
  }

  //TC_1142
  test("TC_1142") {
    sql("CREATE CUBE cube53_drop DIMENSIONS (col1 numeric as col1) MEASURES (Latest_Day numeric as col2) OPTIONS (AGGREGATION [Latest_Day = count])")
    sql("drop cube cube53_drop")
  }

  //DTS2015112610913_03
  test("DTS2015112610913_03") {
    sql("CREATE CUBE cube_restructure61 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructuRE61 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"', FILEHEADER 'a0,b0')");
    sql("alter cube CUBE_restructuRE61 add dimensions(a12 string) measures(b11 integer)");
    sql("drop cube cube_restructure61")
  }

  //TC_1156
  test("TC_1156") {
    sql("create cube vardhan dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan"),
      Seq(Row(100)))
    sql("drop cube vardhan")
  }

  //TC_1163
  test("TC_1163") {
    sql("create cube thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon"),
      Seq(Row(100)))
    sql("drop cube thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon")
  }

  //TC_1166
  test("TC_1166") {
    sql("create cube vardhan15 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan15 partitionData(DELIMITER ';' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan15"),
      Seq(Row(0)))
    sql("drop cube vardhan15")
  }

  //TC_1167
  test("TC_1167") {
    sql("create cube vardhan11 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan11 partitionData(DELIMITER ',' ,FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan11"),
      Seq(Row(100)))
    sql("drop cube vardhan11")
  }

  //TC_1168
  test("TC_1168") {
    sql("create cube vardhan2 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan2 partitionData(DELIMITER ',' ,QUOTECHAR '/', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan2"),
      Seq(Row(100)))
    sql("drop cube vardhan2")
  }

  //TC_1170
  test("TC_1170") {
    sql("create cube vardhan200 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei,AMSize) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan200 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan200"),
      Seq(Row(100)))
    sql("drop cube vardhan200")
  }

  //TC_1171
  test("TC_1171") {
    sql("create cube vardhan500 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string, productionDate TIMESTAMP)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData6.csv' INTO Cube vardhan500 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    checkAnswer(
      sql("select count(*) from vardhan500"),
      Seq(Row(100)))
    sql("drop cube vardhan500")
  }

  //TC_1172
  test("TC_1172") {
    sql("create cube vardhan1000 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData5.csv' INTO Cube vardhan1000 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan1000"),
      Seq(Row(97)))
    sql("drop cube vardhan1000")
  }

  //TC_1173
  test("TC_1173") {
    sql("create cube vardhan9 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan9 partitionData(DELIMITER ',' ,QUOTECHAR '/', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan9"),
      Seq(Row(100)))
    sql("drop cube vardhan9")
  }

  //TC_1174
  test("TC_1174") {
    sql("create cube vardhan16 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan16 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan16"),
      Seq(Row(100)))
    sql("drop cube vardhan16")
  }

  //TC_1175
  test("TC_1175") {
    sql("create schema IF NOT EXISTS myschema1")
    sql("create cube myschema1.vardhan17 DIMENSIONS (AMSize STRING) MEASURES (deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId = count])")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube myschema1.vardhan17 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*)  from myschema1.vardhan17"),
      Seq(Row(100)))
    sql("drop cube myschema1.vardhan17")
    sql("drop schema myschema1")
  }

  //TC_1176
  test("TC_1176") {
    sql("create cube vardhan dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan"),
      Seq(Row(100)))
    sql("drop cube vardhan")
  }

  //TC_1183
  test("TC_1183") {
    sql("create cube thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon"),
      Seq(Row(100)))
    sql("drop cube thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon")
  }

  //TC_1186
  test("TC_1186") {
    sql("create cube vardhan15 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan15 partitionData(DELIMITER ';' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan15"),
      Seq(Row(0)))
    sql("drop cube vardhan15")
  }

  //TC_1187
  test("TC_1187") {
    sql("create cube vardhan11 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan11 partitionData(DELIMITER ',' ,FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan11"),
      Seq(Row(100)))
    sql("drop cube vardhan11")
  }

  //TC_1188
  test("TC_1188") {
    sql("create cube vardhan2 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan2 partitionData(DELIMITER ',' ,QUOTECHAR '/', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan2"),
      Seq(Row(100)))
    sql("drop cube vardhan2")
  }

  //TC_1190
  test("TC_1190") {
    sql("create cube vardhan200 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei,AMSize) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan200 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan200"),
      Seq(Row(100)))
    sql("drop cube vardhan200")
  }

  //TC_1191
  test("TC_1191") {
    sql("create cube vardhan500 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string, productionDate TIMESTAMP)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData6.csv' INTO Cube vardhan500 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    checkAnswer(
      sql("select count(*) from vardhan500"),
      Seq(Row(100)))
    sql("drop cube vardhan500")
  }

  //TC_1192
  test("TC_1192") {
    sql("create cube vardhan1000 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData5.csv' INTO Cube vardhan1000 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan1000"),
      Seq(Row(97)))
    sql("drop cube vardhan1000")
  }

  //TC_1193
  test("TC_1193") {
    sql("create cube vardhan9 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan9 partitionData(DELIMITER ',' ,QUOTECHAR '/', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan9"),
      Seq(Row(100)))
    sql("drop cube vardhan9")
  }

  //TC_1194
  test("TC_1194") {
    sql("create cube vardhan16 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube vardhan16 partitionData(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan16"),
      Seq(Row(100)))
    sql("drop cube vardhan16")
  }

  //TC_1195
  test("TC_1195") {
    sql("create cube myschema.vardhan17 DIMENSIONS (AMSize STRING) MEASURES (deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId = count])")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube myschema.vardhan17 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*)  from myschema.vardhan17"),
      Seq(Row(100)))
    sql("drop cube myschema.vardhan17")
  }

  //DTS2015111808892
  test("DTS2015111808892") {
    sql("CREATE CUBE cube_restructure DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"')")
    checkAnswer(
      sql("select count(*)  from cube_restructure"),
      Seq(Row(100)))
    sql("drop cube cube_restructure")
  }

  //DTS2015111809054
  test("DTS2015111809054") {
    sql("create cube cubeDTS2015111809054 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE cubeDTS2015111809054 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")
    checkAnswer(
      sql("select count(*)  from cubeDTS2015111809054"),
      Seq(Row(21)))
    sql("drop cube cubeDTS2015111809054")
  }

  //DTS2015112006803_01
  test("DTS2015112006803_01") {
    sql("CREATE CUBE incloading_DTS2015112006803_01 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE incloading_DTS2015112006803_01 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"')")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE incloading_DTS2015112006803_01 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"')")
    checkAnswer(
      sql("select count(*) from incloading_DTS2015112006803_01"),
      Seq(Row(200)))
    sql("drop cube incloading_DTS2015112006803_01")
  }


  //DTS2015112710336
  test("DTS2015112710336") {
    sql("create cube rock dimensions(key string as col1,name string as col3) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=col1) INCLUDE ( col1,col3)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE rock OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    checkAnswer(
      sql("select count(*)  from rock"),
      Seq(Row(21)))
    sql("drop cube rock")
  }

  //DTS2015111810813
  test("DTS2015111810813") {
    sql("create cube single dimensions(imei string,deviceInformationId integer,mac string,productdate timestamp,updatetime timestamp) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA fact from './src/test/resources/vmallFact_headr.csv' INTO CUBE single PARTITIONDATA(DELIMITER '\001', QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,mac,productdate,updatetime,gamePointId,contractNumber')")
    checkAnswer(
      sql("select count(*) from single"),
      Seq(Row(10)))
    sql("drop cube single")
  }

  //DTS2015101504861
  test("DTS2015101504861") {
    sql("create cube vard970 dimensions(imei string,productionDate timestamp,AMSize string,channelsId string,ActiveCountry string, Activecity string) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData2.csv' INTO CUBE vard970 OPTIONS(DELIMITER ',', QUOTECHAR '\"', FILEHEADER 'imei,productionDate,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select imei from vard970 where productionDate='2015-07-06 12:07:00'"),
      Seq())
    sql("drop cube vard970")
  }

  //DTS2015101209623
  test("DTS2015101209623") {
    sql("create cube vard971 dimensions(imei string,productionDate timestamp,AMSize string,channelsId string,ActiveCountry string, Activecity string) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData2.csv' INTO CUBE vard971 OPTIONS(DELIMITER ',', QUOTECHAR '\"', FILEHEADER 'imei,productionDate,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select imei from vard971 WHERE gamepointId is NULL"),
      Seq())
    sql("drop cube vard971")
  }

  //TC_1326
  test("TC_1326") {
    sql("create cube vardhanincomp dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData4.csv' INTO Cube vardhanincomp OPTIONS(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select channelsId from vardhanincomp order by imei ASC limit 0"),
      Seq())
    sql("drop cube vardhanincomp")
  }

  //TC_1327
  test("TC_1327") {
    sql("create cube vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq(Row(21)))
    sql("drop cube vardhan01")
  }

  //TC_1328
  test("TC_1328") {
    sql("create cube vardhan01 dimensions(key string,name string as col1) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,col1)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq(Row(21)))
    sql("drop cube vardhan01")
  }


  //DTS2015112009008
  test("DTS2015112009008") {
    sql("CREATE CUBE cube_restructure60 DIMENSIONS (AMSize STRING) MEASURES (Latest_DAY INTEGER) OPTIONS (AGGREGATION [Latest_DAY = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (AMSize) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/create_cube.csv' INTO CUBE cube_restructure60 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"')")
    sql("alter cube cube_restructure60 add dimensions(a1 string)")
    sql("select count(*) from cube_restructure60")
    sql("alter cube cube_restructure60 drop(a1)")
    checkAnswer(
      sql("select count(*) from cube_restructure60"),
      Seq(Row(200)))
    sql("drop cube cube_restructure60")
  }

  //DTS2015120304016
  test("DTS2015120304016") {
    sql("CREATE CUBE incloading1 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE incloading1 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("drop cube incloading1")
    sql("CREATE CUBE incloading1 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE incloading1 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    checkAnswer(
      sql("select count(*) from incloading1"),
      Seq(Row(100)))
    sql("drop cube incloading1")
  }

  //DTS2015110311277
  test("DTS2015110311277") {
    sql("create cube vardhan dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube vardhan OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube vardhan add dimensions(alreadID)")
    sql("alter cube vardhan drop (alreadID)")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube vardhan OPTIONS(DELIMITER ',' ,QUOTECHAR '\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from vardhan"),
      Seq(Row(200)))
    sql("drop cube vardhan")
  }

  //DTS2015121511752
  test("DTS2015121511752") {
    sql("CREATE CUBE cube_restructure68 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure68 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure68 add dimensions(a10 string) measures(b9 integer) options (AGGREGATION [b9 = MAX])")
    sql(" drop cube cube_restructure68")
    sql("CREATE CUBE cube_restructure68 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure68 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"')")
    checkAnswer(
      sql("select * from cube_restructure68 order by a0 ASC limit 3"),
      Seq(Row("1AA1",2738.0),Row("1AA10",1714.0),Row("1AA100",1271.0)))
    sql("drop cube cube_restructure68")
  }

  //TC_1329
  test("TC_1329") {
    sql("create cube vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("alter cube vardhan01 add dimensions(productiondate timestamp) options (AGGREGATION [b = SUM])")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")
    checkAnswer(
      sql("select key from vardhan01 order by key ASC limit 1"),
      Seq(Row("1")))
    sql("drop cube vardhan01")
  }

  //TC_1330
  test("TC_1330") {
    sql("create cube vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")
    sql("alter cube vardhan01 add dimensions(productiondate timestamp) options (AGGREGATION [b = SUM])")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq(Row(42)))
    sql("drop cube vardhan01")
  }

  //TC_1331
  test("TC_1331") {
    sql("create cube vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")
    sql("alter cube vardhan01 add dimensions(productiondate timestamp as col5) options (AGGREGATION [b = SUM])")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"', FILEHEADER '')")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq(Row(42)))
    sql("drop cube vardhan01")
  }

  //TC_1196
  test("TC_1196") {
    sql("CREATE CUBE cube_restructure1 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure1 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure1 add dimensions(a string) measures(b integer)")
    checkAnswer(
      sql("select a,b from cube_restructure1 limit 1"),
      Seq(Row(null,0.0)))
    sql("drop cube cube_restructure1")
  }

  //TC_1197
  test("TC_1197") {
    sql("CREATE CUBE cube_restructure2 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure2 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure2 add dimensions(a39 string)")
    checkAnswer(
      sql("select distinct(a39) from cube_restructure2"),
      Seq(Row(null)))
    sql("drop cube cube_restructure2")
  }

  //TC_1198
  test("TC_1198") {
    sql("CREATE CUBE cube_restructure3 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure3 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure3 add measures(b1 integer)")
    checkAnswer(
      sql("select distinct(b1) from cube_restructure3"),
      Seq(Row(0.0)))
    sql("drop cube cube_restructure3")
  }

  //TC_1200
  test("TC_1200") {
    sql("CREATE CUBE cube_restructure5 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure5 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure5 add measures(b2 integer) options (AGGREGATION [b2 = SUM])")
    checkAnswer(
      sql("select distinct(b2) from cube_restructure5"),
      Seq(Row(0.0)))
    sql("drop cube cube_restructure5")
  }

  //TC_1204
  test("TC_1204") {
    sql("CREATE CUBE cube_restructure9 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure9 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure9 add dimensions(a8 string) measures(b7 integer) options (AGGREGATION [b7 = COUNT])")
    checkAnswer(
      sql("select a8,b7 from cube_restructure9 limit 1"),
      Seq(Row(null,0.0)))
    sql("drop cube cube_restructure9")
  }

  //TC_1205
  test("TC_1205") {
    sql("CREATE CUBE cube_restructure10 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure10 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure10 add dimensions(a9 string) measures(b8 integer) options (AGGREGATION [b8 = MIN])")
    checkAnswer(
      sql("select a9,b8 from cube_restructure10 limit 1"),
      Seq(Row(null,0.0)))
    sql("drop cube cube_restructure10")
  }

  //TC_1206
  test("TC_1206") {
    sql("CREATE CUBE cube_restructure11 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure11 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure11 add dimensions(a10 string) measures(b9 integer) options (AGGREGATION [b9 = MAX])")
    checkAnswer(
      sql("select a10,b9 from cube_restructure11 limit 1"),
      Seq(Row(null,0.0)))
    sql("drop cube cube_restructure11")
  }

  //TC_1207
  test("TC_1207") {
    sql("CREATE CUBE cube_restructure12 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure12 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure12 add dimensions(a11 string) measures(b10 integer) options (AGGREGATION [b10 = AVG])")
    checkAnswer(
      sql("select a11,b10 from cube_restructure12 limit 1"),
      Seq(Row(null,0.0)))
    sql("drop cube cube_restructure12")
  }

  //TC_1208
  test("TC_1208") {
    sql("CREATE CUBE cube_restructure13 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure13 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure13 add dimensions(a12 string) measures(b11 integer) options (defaults [a12=test])")
    checkAnswer(
      sql("select a12,b11 from cube_restructure13 limit 1"),
      Seq(Row("test",0.0)))
    sql("drop cube cube_restructure13")
  }

  //TC_1209
  test("TC_1209") {
    sql("CREATE CUBE cube_restructure14 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure14 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure14 add dimensions(a13 string) measures(b12 integer) options (defaults [thisisalongnamethisisalongnamethisisalongnamethisisalongnamethisisalongnamethisisalongnamethisisalongname=test])")
    checkAnswer(
      sql("select a13,b12 from cube_restructure14 limit 1"),
      Seq(Row(null,0.0)))
    sql("drop cube cube_restructure14")
  }

  //TC_1210
  test("TC_1210") {
    sql("CREATE CUBE cube_restructure15 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure15 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure15 add dimensions(a14 string) measures(b13 integer) options (defaults [a13=人转])")
    checkAnswer(
      sql("select a14,b13 from cube_restructure15 limit 1"),
      Seq(Row(null,0.0)))
    sql("drop cube cube_restructure15")
  }

  //TC_1211
  test("TC_1211") {
    sql("CREATE CUBE cube_restructure16 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure16 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure16 add dimensions(a15 string) measures(b14 integer) options (defaults [b14=10])")
    checkAnswer(
      sql("select a15,b14 from cube_restructure16 limit 1"),
      Seq(Row(null,10.0)))
    sql("drop cube cube_restructure16")
  }

  //TC_1212
  test("TC_1212") {
    sql("CREATE CUBE cube_restructure17 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure17 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure17 add dimensions(a16 string) measures(b13 integer) options (defaults [a16=10])")
    checkAnswer(
      sql("select a16,b13 from cube_restructure17 limit 1"),
      Seq(Row("10",0.0)))
    sql("drop cube cube_restructure17")
  }

  //TC_1213
  test("TC_1213") {
    sql("CREATE CUBE cube_restructure18 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure18 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure18 add dimensions(a17 string) measures(b14 integer) options (defaults [b14=test])")
    checkAnswer(
      sql("select a17,b14 from cube_restructure18 limit 1"),
      Seq(Row(null,0.0)))
    sql("drop cube cube_restructure18")
  }

  //TC_1214
  test("TC_1214") {
    sql("CREATE CUBE cube_restructure19 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure19 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure19 add dimensions(a18 string) measures(b15 integer) options (defaults [a18=test,b15=10])")
    checkAnswer(
      sql("select a18,b15 from cube_restructure19 limit 1"),
      Seq(Row("test",10.0)))
    sql("drop cube cube_restructure19")
  }

  //TC_1217
  test("TC_1217") {
    sql("CREATE CUBE cube_restructure22 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure22 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure22 add dimensions(a40 string) measures(b40 integer) options (AGGREGATION [b40 = AVG] defaults [a40=test])")
    checkAnswer(
      sql("select a40,b40 from cube_restructure22 limit 1"),
      Seq(Row("test",0.0)))
    sql("drop cube cube_restructure22")
  }

  //TC_1218
  test("TC_1218") {
    sql("CREATE CUBE cube_restructure23 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure23 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure23 add dimensions(a40 string) measures(b40 integer) options (AGGREGATION [b40 = AVG] defaults [a40=test])")
    sql("alter cube cube_restructure23 drop (b40)")
    checkAnswer(
      sql("select * from cube_restructure23 limit 1"),
      Seq(Row("1AA1","test",2738.0)))
    sql("drop cube cube_restructure23")
  }

  //TC_1219
  test("TC_1219") {
    sql("CREATE CUBE cube_restructure24 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure24 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure24 add dimensions(a40 string) measures(b40 integer) options (AGGREGATION [b40 = AVG] defaults [a40=test])")
    sql("alter cube cube_restructure24 drop (a40)")
    checkAnswer(
      sql("select * from cube_restructure24 limit 1"),
      Seq(Row("1AA1",2738.0,0.0)))
    sql("drop cube cube_restructure24")
  }

  //TC_1220
  test("TC_1220") {
    sql("CREATE CUBE cube_restructure25 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure25 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure25 add dimensions(a40 string) measures(b40 integer) options (AGGREGATION [b40 = AVG] defaults [a40=test])")
    sql("alter cube cube_restructure25 drop (a40,b40)")
    checkAnswer(
      sql("select * from cube_restructure25 limit 1"),
      Seq(Row("1AA1",2738.0)))
    sql("drop cube cube_restructure25")
  }

  //TC_1223
  test("TC_1223") {
    sql("CREATE schema IF NOT EXISTS  res")
    sql("CREATE CUBE res.cube_restructure27 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE res.cube_restructure27 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube res.cube_restructure27 add dimensions(a40 string) measures(b40 integer) options (AGGREGATION [b40 = AVG] defaults [a40=test])")
    checkAnswer(
      sql("select a40,b40 from res.cube_restructure27 limit 1"),
      Seq(Row("test",0.0)))
    sql("drop cube res.cube_restructure27")
  }

  //TC_1224
  test("TC_1224") {
    sql("CREATE CUBE res.cube_restructure29 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE res.cube_restructure29 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube res.cube_restructure29 add dimensions(a40 string) measures(b40 integer) options (AGGREGATION [b40 = AVG] defaults [a40=test])")
    sql("alter cube res.cube_restructure29 drop (a40,b40)")
    checkAnswer(
      sql("select a0,b0 from res.cube_restructure29 limit 1"),
      Seq(Row("1AA1",2738.0)))
    sql("drop cube res.cube_restructure29")
  }

  //TC_1225
  test("TC_1225") {
    sql("CREATE CUBE cube_restructure_alias30 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias30 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias30 add dimensions(a string as alias3) measures(b integer as alias4) options (AGGREGATION [alias4 = SUM])")
    checkAnswer(
      sql("select * from cube_restructure_alias30 limit 1"),
      Seq(Row("1AA1",null,2738.0,0.0)))
    sql("drop cube cube_restructure_alias30")
  }

  //TC_1226
  test("TC_1226") {
    sql("CREATE CUBE cube_restructure_alias31 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias31 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias31 add dimensions(a1 string as alias5) measures(b integer as alias6) options (AGGREGATION [alias6 = COUNT])")
    checkAnswer(
      sql("select * from cube_restructure_alias31 limit 1"),
      Seq(Row("1AA1",null,2738.0,0.0)))
    sql("drop cube cube_restructure_alias31")
  }

  //TC_1227
  test("TC_1227") {
    sql("CREATE CUBE cube_restructure_alias32 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias32 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias32 add dimensions(a1 string as alias7) measures(b integer as alias8) options (AGGREGATION [alias8 = MIN])")
    checkAnswer(
      sql("select * from cube_restructure_alias32 limit 1"),
      Seq(Row("1AA1",null,2738.0,0.0)))
    sql("drop cube cube_restructure_alias32")
  }

  //TC_1228
  test("TC_1228") {
    sql("CREATE CUBE cube_restructure_alias33 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias33 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias33 add dimensions(a1 string as alias9) measures(b integer as alias10) options (AGGREGATION [alias10 = MAX])")
    checkAnswer(
      sql("select * from cube_restructure_alias33 limit 1"),
      Seq(Row("1AA1",null,2738.0,0.0)))
    sql("drop cube cube_restructure_alias33")
  }

  //TC_1229
  test("TC_1229") {
    sql("CREATE CUBE cube_restructure_alias34 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias34 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias34 add dimensions(a1 string as alias11) measures(b integer as alias12) options (AGGREGATION [alias12 = AVG])")
    checkAnswer(
      sql("select * from cube_restructure_alias34 limit 1"),
      Seq(Row("1AA1",null,2738.0,0.0)))
    sql("drop cube cube_restructure_alias34")
  }

  //TC_1230
  test("TC_1230") {
    sql("CREATE CUBE cube_restructure_alias35 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias35 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias35 add dimensions(a1 string as alias13) measures(b1 integer as alias14) options (defaults [alias14=test])")
    checkAnswer(
      sql("select * from cube_restructure_alias35 limit 1"),
      Seq(Row("1AA1",null,2738.0,0.0)))
    sql("drop cube cube_restructure_alias35")
  }

  //TC_1231
  test("TC_1231") {
    sql("CREATE CUBE cube_restructure_alias36 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias36 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias36 add dimensions(a1 string as alias15) measures(b1 integer as alias16) options (defaults [alias15=test])")
    checkAnswer(
      sql("select * from cube_restructure_alias36 limit 1"),
      Seq(Row("1AA1","test",2738.0,0.0)))
    sql("drop cube cube_restructure_alias36")
  }

  //TC_1232
  test("TC_1232") {
    sql("CREATE CUBE cube_restructure_alias37 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias37 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias37 add dimensions(a1 string as alias15) measures(b1 integer as alias16) options (defaults [alias15=test])")
    sql("alter cube cube_restructure_alias37 drop (alias15)")
    checkAnswer(
      sql("select * from cube_restructure_alias37 limit 1"),
      Seq(Row("1AA1",2738.0,0.0)))
    sql("drop cube cube_restructure_alias37")
  }

  //TC_1233
  test("TC_1233") {
    sql("CREATE CUBE cube_restructure_alias38 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias38 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias38 add dimensions(a1 string as alias15) measures(b1 integer as alias16) options (defaults [alias15=test])")
    sql("alter cube cube_restructure_alias38 drop (alias16)")
    checkAnswer(
      sql("select * from cube_restructure_alias38 limit 1"),
      Seq(Row("1AA1","test",2738.0)))
    sql("drop cube cube_restructure_alias38")
  }

  //TC_1234
  test("TC_1234") {
    sql("CREATE CUBE cube_restructure_alias39 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure_alias39 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure_alias39 add dimensions(a1 string as alias15) measures(b1 integer as alias16) options (defaults [alias15=test])")
    sql("alter cube cube_restructure_alias39 drop (alias15,alias16)")
    checkAnswer(
      sql("select * from cube_restructure_alias39 limit 1"),
      Seq(Row("1AA1",2738.0)))
    sql("drop cube cube_restructure_alias39")
  }

  //TC_1235
  test("TC_1235") {
    sql("CREATE CUBE cube_restructure40 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure40 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure40 add dimensions(a42 timestamp)")
    checkAnswer(
      sql("select * from cube_restructure40 limit 1"),
      Seq(Row("1AA1",null,2738.0)))
    sql("drop cube cube_restructure40")
  }

  //TC_1236
  test("TC_1236") {
    sql("CREATE CUBE cube_restructure41 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure41 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure41 add dimensions(a43 integer)")
    checkAnswer(
      sql("select * from cube_restructure41 limit 1"),
      Seq(Row("1AA1",null,2738.0)))
    sql("drop cube cube_restructure41")
  }

  //TC_1237
  test("TC_1237") {
    sql("CREATE CUBE cube_restructure42 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure42 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure42 add measures(b42 numeric)")
    checkAnswer(
      sql("select * from cube_restructure42 limit 1"),
      Seq(Row("1AA1",2738.0,0.0)))
    sql("drop cube cube_restructure42")
  }

  //TC_1238
  test("TC_1238") {
    sql("CREATE CUBE cube_restructure43 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure43 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure43 add dimensions(a43 string) measures(b43 integer)")
    sql("refresh table cube_restructure43")
    checkAnswer(
      sql("select * from cube_restructure43 limit 1"),
      Seq(Row("1AA1",null,2738.0,0.0)))
    sql("drop cube cube_restructure43")
  }

  //TC_1239
  test("TC_1239") {
    sql("CREATE CUBE cube_restructure44 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure44 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure44 add dimensions(a44 string) measures(b44 integer)")
    sql("alter cube cube_restructure44 drop(a44,b44)")
    sql("refresh table cube_restructure44")
    checkAnswer(
      sql("select * from cube_restructure44 limit 1"),
      Seq(Row("1AA1",2738.0)))
    sql("drop cube cube_restructure44")
  }

  //TC_1240
  test("TC_1240") {
    sql("CREATE CUBE cube_restructure45 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("alter cube cube_restructure45 add dimensions(a45 string) measures(b45 integer)")
    checkAnswer(
      sql("select * from cube_restructure45 limit 1"),
      Seq())
    sql("drop cube cube_restructure45")
  }

  //TC_1241
  test("TC_1241") {
    sql("CREATE CUBE cube_restructure46 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("alter cube cube_restructure46 add dimensions(a46 string) measures(b46 integer)")
    sql("alter cube cube_restructure46 drop(a46,b46)")
    checkAnswer(
      sql("select * from cube_restructure46 limit 1"),
      Seq())
    sql("drop cube cube_restructure46")
  }

  //TC_1243
  test("TC_1243") {
    sql("CREATE CUBE cube_restructure48 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("alter cube cube_restructure48 add dimensions(a47) measures(b47)")
    checkAnswer(
      sql("select * from cube_restructure48 limit 1"),
      Seq())
    sql("drop cube cube_restructure48")
  }

  //DTS2015103009592
  test("DTS2015103009592") {
    sql("CREATE CUBE cube_restructure55 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure55 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("desc cube_restructure55")
    sql("alter cube cube_restructure55 drop (b0)")
    checkAnswer(
      sql("select * from cube_restructure55 limit 1"),
      Seq(Row("1AA1")))
    sql("drop cube cube_restructure55")
  }

  //DTS2015111309941
  test("DTS2015111309941") {
    sql("CREATE CUBE cube_restructure56 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("alter cube cube_restructure56 add dimensions(a18 string) measures(b18 numeric) options (defaults [b18=10])")
    checkAnswer(
      sql("select * from cube_restructure56 limit 1"),
      Seq())
    sql("drop cube cube_restructure56")
  }

  //DTS2015112509895
  test("DTS2015112509895") {
    sql("CREATE CUBE cube_restructure57 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure57 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure57 add dimensions(a40)")
    checkAnswer(
      sql("select a0 from cube_restructure57 where a40 is NULL limit 1"),
      Seq(Row("1AA1")))
    sql("drop cube cube_restructure57")
  }

  //DTS2015112610913_01
  test("DTS2015112610913_01") {
    sql("CREATE CUBE cube_restructure58 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure58 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"', FILEHEADER 'a0,b0')")
    sql("alter cube cube_restructure58 add dimensions(a12 string) measures(b11 integer)")
    checkAnswer(
      sql("select a0 from cube_restructure58 limit 1"),
      Seq(Row("2738")))
    sql("drop cube cube_restructure58")
  }

  //DTS2015112610913_02
  test("DTS2015112610913_02") {
    sql("CREATE CUBE cube_restructure59 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure59 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"', FILEHEADER 'a0,b0')")
    sql("alter cube cube_restructure59 add dimensions(a12 string) measures(b11 integer)")
    sql("alter cube cube_restructure59 add dimensions(a13 string) measures(b13 integer)")
    checkAnswer(
      sql("select a0 from cube_restructure59 order by a0 limit 1"),
      Seq(Row("1015")))
    sql("drop cube cube_restructure59")
  }

  //DTS2015120209187
  test("DTS2015120209187") {
    sql("CREATE cube t1 dimensions(imei string,deviceInformationId integer,mac string,age integer,productdate timestamp,updatetime timestamp) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("CREATE CUBE t15 DIMENSIONS (imei String, InformationId integer) MEASURES (pointid Numeric, contractnumber Numeric) WITH t1 RELATION (FACT.imei=imei) INCLUDE (imei)")
    sql("ALTER CUBE t15 ADD DIMENSIONS (mac string,age integer) with t1 relation (FACT.imei = imei) include (imei, mac, age)")
    checkAnswer(
      sql("select imei from t15 limit 1"),
      Seq())
    sql("drop cube t1")
  }

  //DTS2015112509746
  test("DTS2015112509746") {
    sql("CREATE cube cube_restructure61 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube cube_restructure61 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube cube_restructure61 add dimensions(a1 string) measures (b1 integer)")
    checkAnswer(
      sql("select AMSize from cube_restructure61 limit 1"),
      Seq(Row("8RAM size")))
    sql("drop cube cube_restructure61")
  }

  //DTS2015110209906
  test("DTS2015110209906") {
    sql("CREATE CUBE cube_restructure63 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure63 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("alter cube cube_restructure63 add dimensions(a10 string) measures(b9 integer) options (AGGREGATION [b9 = MAX])")
    sql("alter cube cube_restructure63 add dimensions(a11 string) measures(b10 integer) options (AGGREGATION [b10 = AVG])")
    sql("select * from cube_restructure63 limit 10")
    sql("refresh table cube_restructure63")
    sql("select * from cube_restructure63 limit 10")
    sql("alter cube cube_restructure63 add dimensions(a12 string) measures(b11 integer) options (AGGREGATION [b10 = AVG])")
    sql("drop cube cube_restructure63")
    sql("CREATE CUBE cube_restructure63 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure63 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("refresh table cube_restructure63")
    sql("select * from cube_restructure63 limit 10")
    sql("alter cube cube_restructure63 add dimensions(a string)")
    checkAnswer(
      sql("select a0 from cube_restructure63 limit 1"),
      Seq(Row("1AA1")))
    sql("drop cube cube_restructure63")
  }

  //DTS2015110300006
  test("DTS2015110300006") {
    sql("CREATE CUBE cube_restructure64 DIMENSIONS (a0 STRING as alias1) MEASURES (b0 INTEGER as alias2) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (alias1) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure64 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"')")
    sql("select count(*) from cube_restructure64")
    sql("alter cube cube_restructure64 add dimensions(a string as alias3) measures(b integer as alias4) options (AGGREGATION [alias4 = SUM])")
    sql("select count(*) from cube_restructure64")
    sql("alter cube cube_restructure64 add dimensions(a1 string as alias5) measures(b111 integer as alias6) options (AGGREGATION [alias6 = COUNT])")
    checkAnswer(
      sql("select count(*) from cube_restructure64"),
      Seq(Row(100)))
    sql("drop cube cube_restructure64")
  }

  //DTS2015120405916
  test("DTS2015120405916") {
    sql("CREATE cube vardhan1 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube vardhan1 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube vardhan1 drop (deviceInformationId)")
    sql("alter cube vardhan1 add  measures(deviceInformationId integer) options ()")
    checkAnswer(
      sql("SELECT imei FROM vardhan1 order by imei LIMIT 1"),
      Seq(Row("1AA1")))
    sql("drop cube vardhan1")
  }

  //DTS2015120405916_1
  test("DTS2015120405916_1") {
    sql("CREATE cube vardhan1 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube vardhan1 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube vardhan1 drop (ActiveCountry)")
    sql("alter cube vardhan1 add  dimensions(ActiveCountry string) options ()")
    checkAnswer(
      sql("SELECT channelsId FROM vardhan1 order by imei LIMIT 1"),
      Seq(Row("4")))
    sql("drop cube vardhan1")
  }

  //DTS2015110309063
  test("DTS2015110309063") {
    sql("CREATE cube vardhan dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube vardhan OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube vardhan  add dimensions(alreadID)")
    checkAnswer(
      sql("select count(*) from vardhan where alreadID is null"),
      Seq(Row(100)))
    sql("drop cube vardhan")
  }

  //TC_1333
  test("TC_1333") {
    sql("CREATE cube vardhanretention01 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string as prsntcntry, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer as deviceid) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention01 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("alter cube vardhanretention01 drop (prsntcntry)")
    checkAnswer(
      sql("select imei from vardhanretention01 order by imei desc limit 1"),
      Seq(Row("1AA100084")))
    sql("drop cube vardhanretention01")
  }

  //TC_1334
  test("TC_1334") {
    sql("CREATE cube vardhanretention dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("alter cube vardhanretention drop(AMSize) add dimensions(AMSize string)")
    checkAnswer(
      sql("select count(*) from vardhanretention"),
      Seq(Row(100)))
    sql("drop cube vardhanretention")
  }

  //TC_1335
  test("TC_1335") {
    sql("CREATE cube vardhanretention dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("alter cube vardhanretention drop(AMSize) add dimensions(AMSize string)")
    checkAnswer(
      sql("select imei from vardhanretention where imei='1AA100'"),
      Seq(Row("1AA1",0.0)))
    sql("drop cube vardhanretention")
  }

  //DTS2015120502719_01
  test("DTS2015120502719_01") {
    try
    {
      sql("CREATE schema IF NOT EXISTS test1")
      sql("create cube test1.t5 dimensions(imei string, productdate timestamp,updatetime timestamp) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
      sql("create cube t5 dimensions(imei string, productdate timestamp,updatetime timestamp) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )\"")
      sql("alter cube t5 add dimensions (name string,age integer) measures(sales integer) with test1 relation (FACT.name = imei) include (mac)")
      fail("Unexpected behavior")
    }
    catch
      {
        case ex: Throwable =>sql("drop cube test1")
          sql("drop cube t5")
          sql("drop schema test1")


      }
  }

  //DTS2015120502719_02
  test("DTS2015120502719_02") {
    try
    {
      sql("CREATE schema IF NOT EXISTS test1")
      sql("create cube test1.t6 dimensions(imei string, productdate timestamp,updatetime timestamp) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
      sql("LOAD DATA FACT FROM './src/test/resources/test12.csv' INTO CUBE test1.t6 PARTITIONDATA (DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,productdate,updatetime,gamePointId,contractNumber')")
      sql("create cube t6 dimensions(imei string, productdate timestamp,updatetime timestamp) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
      sql("LOAD DATA FACT FROM './src/test/resources/test12.csv' INTO CUBE t6 PARTITIONDATA (DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,productdate,updatetime,gamePointId,contractNumber')\"")
      sql("alter cube t6 add dimensions (name string,age integer) measures(sales integer) with test1 relation (FACT.name = imei) include (mac)")
      fail("Unexpected behavior")
    }
    catch
      {
        case ex: Throwable =>
          sql("drop cube  test1.t6")
          sql("drop cube t6")
          sql("drop schema test1")


      }
  }

  //DTS2015120502719
  test("DTS2015120502719") {
    try
    {
      sql("CREATE schema test")
      sql("create cube vardhan dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
      sql("create cube test.vardhan dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
      sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube vardhan OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
      sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube test.vardhan OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId') \"")
      sql("alter cube vardhan add dimensions (name string,age integer) measures(sales integer) with test1 relation (FACT.name = imei) include (mac)")
      fail("Unexpected behavior")
    }
    catch
      {
        case ex: Throwable =>sql("drop cube vardhan")
          sql("drop cube test.vardhan")
          sql("drop schema test")

      }
  }

  //DTS2015121710412
  test("DTS2015121710412") {
    try
    {
      sql("CREATE schema test")
      sql("create cube vardhan dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
      sql("create cube test.vardhan dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
      sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube vardhan OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
      sql("LOAD DATA FACT FROM './src/test/resources/TestData1.csv' INTO Cube test.vardhan OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId') \"")
      sql("alter cube vardhan add dimensions (name string,age integer) measures(sales integer) with test1 relation (FACT.name = imei) include (name)")
      fail("Unexpected behavior")
    }
    catch
      {
        case ex: Throwable =>sql("drop cube vardhan")
          sql("drop schema test")

      }
  }

  //TC_1246
  test("TC_1246") {
    sql("create cube cube1 DIMENSIONS (imei string,deviceInformationId integer,MAC string,deviceColor string, device_backColor string,modelId string, marketName string, AMSize string, ROMSize string, CUPAudit string, CPIClocked string, series string, productionDate string, bomCode string, internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince  string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict  string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR  integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) MEASURES (gamePointId numeric,contractNumber numeric) OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO CUBE cube1 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("show loads for cube cube1"),
      Seq(Row("0","Success","2015-11-05 14:27:10.0"," 2015-11-05 14:27:10.0")))
    sql("drop cube cube1")
  }

  //TC_1248
  test("TC_1248") {
    sql("create cube cube3 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube3 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("show loads for cube cube3"),
      Seq(Row("0","Success","2015-11-05 15:15:42.0"," 2015-11-05 15:15:43.0")))
    sql("drop cube cube3")
  }

  //TC_1249
  test("TC_1249") {
    sql("create cube myschema1.cube4 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube myschema1.cube4 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("SHOW LOADS for cube myschema1.cube4"),
      Seq(Row("0","Success","2015-11-05 15:23:02.0"," 2015-11-05 15:23:03.0")))
    sql("drop cube myschema1.cube4")
  }

  //TC_1250
  test("TC_1250") {
    sql("create cube cube5 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei,AMSize) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube5 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("show loads for cube cube5"),
      Seq(Row("0","Success","2015-11-05 15:23:02.0"," 2015-11-05 15:23:03.0")))
    sql("drop cube cube5")
  }

  //TC_1251
  test("TC_1251") {
    sql("create cube cube6 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube6 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("show loads for cube cube6"),
      Seq(Row("0","Success","2015-11-05 15:23:02.0"," 2015-11-05 15:23:03.0")))
    sql("drop cube cube6")
  }

  //TC_1252
  test("TC_1252") {
    sql("create cube cube7 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    checkAnswer(
      sql("show loads for cube cube7"),
      Seq())
    sql("drop cube cube7")
  }

  //TC_1253
  test("TC_1253") {
    sql("create cube cube123 DIMENSIONS (imei string,deviceInformationId integer,MAC string,deviceColor string, device_backColor string,modelId string, marketName string, AMSize string, ROMSize string, CUPAudit string, CPIClocked string, series string, productionDate string, bomCode string, internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince  string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict  string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR  integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) MEASURES (gamePointId numeric,contractNumber numeric) OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (imei) ,PARTITION_COUNT=2] )")
    checkAnswer(
      sql("show loads for cube cube123"),
      Seq())
    sql("drop cube cube123")
  }

  //TC_1254
  test("TC_1254") {
    sql("create cube cube9 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube9 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube9 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("show loads for cube cube9"),
      Seq(Row("0","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("1","Success","2015-11-05 17:43:43.0"," 2015-11-05 17:43:44.0")))
    sql("drop cube cube9")
  }

  //TC_1257
  test("TC_1257") {
    sql("create cube cube12 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    checkAnswer(
      sql("show loads for cube cube12"),
      Seq())
    sql("drop cube cube12")
  }

  //TC_1258
  test("TC_1258") {
    sql("create cube cube13 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube13 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("sHOw LoaDs for cube cube13"),
      Seq(Row("0","Success","2015-11-05 18:09:40.0"," 2015-11-05 18:09:41.0")))
    sql("drop cube cube13")
  }

  //DTS2015112006803_02
  test("DTS2015112006803_02") {
    sql("create cube cube14 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube14 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("select * from cube14")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube14 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("select * from cube14")
    checkAnswer(
      sql("show loads for cube cube14"),
      Seq(Row("1","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("0","Success","2015-11-05 17:43:43.0"," 2015-11-05 17:43:44.0")))
    sql("drop cube cube14")
  }

  //DTS2015110901347
  test("DTS2015110901347") {
    sql("create cube cube15 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube15 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube15 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube15 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube15 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("show loads for cube cube15"),
      Seq(Row("3","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("2","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("1","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("0","Success","2015-11-05 17:43:43.0"," 2015-11-05 17:43:44.0")))
    sql("drop cube cube15")
  }

  //DTS2015121707872
  test("DTS2015121707872") {
    sql("create cube t202 dimensions(imei string,deviceInformationId integer,mac string,productdate timestamp,updatetime timestamp) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/test1t.csv' INTO CUBE t202 OPTIONS (DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,mac,productdate,updatetime,gamePointId,contractNumber')")
    checkAnswer(
      sql("show loads for cube t202"),
      Seq())
    sql("drop cube t202")
  }

  //TC_1336
  test("TC_1336") {
    sql("create cube vardhanretention123 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("alter cube vardhanretention123 drop(AMSize) add dimensions(AMSize string)")
    checkAnswer(
      sql("show loads for cube vardhanretention123"),
      Seq())
    sql("drop cube vardhanretention123")
  }




  //TC_1271
  test("TC_1271") {
    sql("create cube cube123 DIMENSIONS (imei string,deviceInformationId integer,MAC string,deviceColor string, device_backColor string,modelId string, marketName string, AMSize string, ROMSize string, CUPAudit string, CPIClocked string, series string, productionDate string, bomCode string, internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince  string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict  string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR  integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) MEASURES (gamePointId numeric,contractNumber numeric) OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO CUBE cube123 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("create aggregatetable Latest_YEAR,count(contractNumber)from cube cube123")
    checkAnswer(
      sql("select contractNumber from cube123 where contractNumber=5281803.0"),
      Seq(Row(5281803.0)))
    sql("drop cube cube123")
  }

  //TC_1275
  test("TC_1275") {
    sql("create cube cube5 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei,AMSize) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube5 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable imei,sum(deviceInformationId)from cube cube5")
    checkAnswer(
      sql("select imei from cube5 where imei=\"1AA1\""),
      Seq(Row("1AA1")))
    sql("drop cube cube5")
  }

  //TC_1276
  test("TC_1276") {
    sql("create cube cube6 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube6 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable  AMSize,sum(deviceInformationId) from cube cube6")
    checkAnswer(
      sql("select AMSize from cube6 limit 1"),
      Seq(Row("8RAM size")))
    sql("drop cube cube6")
  }

  //TC_1277
  test("TC_1277") {
    sql("create cube cube7 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("create aggregatetable AMSize,sum(deviceInformationId) from cube cube7")
    checkAnswer(
      sql("select AMSize from cube7"),
      Seq())
    sql("drop cube cube7")
  }

  //TC_1278
  test("TC_1278") {
    sql("create cube cube8 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("alter cube cube8 add dimensions(a1 string) measures (b1 integer)")
    sql("create aggregatetable AMSize,sum(deviceInformationId) from cube cube8")
    checkAnswer(
      sql("select deviceInformationId from cube8"),
      Seq())
    sql("drop cube cube8")
  }

  //TC_1279
  test("TC_1279") {
    sql("create cube cube9 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("alter cube cube9 add dimensions(a1 string) measures (b1 integer)")
    sql("alter cube cube9 drop(a1,b1)")
    sql("create aggregatetable AMSize,sum(deviceInformationId) from cube cube9")
    checkAnswer(
      sql("select AMSize from cube9"),
      Seq())
    sql("drop cube cube9")
  }

  //TC_1280
  test("TC_1280") {
    sql("create cube cube10 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube10 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube cube10 add dimensions(a1 string) measures (b1 integer)")
    sql("alter cube cube10 drop(a1,b1)")
    sql("create aggregatetable imei,sum(deviceInformationId)from cube cube10")
    checkAnswer(
      sql("select imei from cube10 where imei=\"1AA1\""),
      Seq(Row("1AA1")))
    sql("drop cube cube10")
  }

  //TC_1281
  test("TC_1281") {
    sql("create cube cube11 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube11 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube cube11 add dimensions(a1 string) measures (b1 integer)")
    sql("alter cube cube11 drop(a1,b1)")
    sql("create aggregatetable AMSize,sum(gamePointId)from cube cube11")
    checkAnswer(
      sql("select imei from cube11  where imei=\"1AA1\""),
      Seq(Row("1AA1")))
    sql("drop cube cube11")
  }

  //TC_1282
  test("TC_1282") {
    sql("create cube cube12 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube12 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube12 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable channelsId,sum(gamePointId)from cube cube12")
    checkAnswer(
      sql("select channelsId from cube12  where channelsId=\"4\" limit 1"),
      Seq(Row(4)))
    sql("drop cube cube12")
  }

  //TC_1283
  test("TC_1283") {
    sql("create cube cube13 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube13 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube13 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable imei,max(deviceInformationId)from cube cube13")
    checkAnswer(
      sql("select imei from cube13 where imei=\"1AA100001\" limit 1"),
      Seq(Row("1AA100001")))
    sql("drop cube cube13")
  }

  //TC_1284
  test("TC_1284") {
    sql("create cube cube14 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube14 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube cube14 add dimensions(a1 string) measures (b1 integer)")
    sql("alter cube cube14 drop(a1,b1)")
    sql("create aggregatetable channelsId,min(deviceInformationId)from cube cube14")
    checkAnswer(
      sql("select channelsId from cube14 where channelsId=\"4\" limit 1"),
      Seq(Row(4)))
    sql("drop cube cube14")
  }

  //TC_1285
  test("TC_1285") {
    sql("create cube cube15 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("alter cube cube15 add dimensions(a1 string) measures(b1 integer)")
    sql("alter cube cube15 add dimensions(a2 string) measures(b2 integer)")
    sql("create aggregatetable channelsId,max(deviceInformationId)from cube cube15")
    checkAnswer(
      sql("select channelsId from cube15"),
      Seq())
    sql("drop cube cube15")
  }

  //TC_1286
  test("TC_1286") {
    sql("create cube cube16 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube16 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube16 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable channelsId,max(gamePointId)from cube cube16")
    checkAnswer(
      sql("select gamePointId from cube16 where gamePointId=2738.0 limit 1"),
      Seq(Row(2738.0)))
    sql("drop cube cube16")
  }


  //TC_1288
  test("TC_1288") {
    sql("create cube cube18 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube18 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable ActiveCountry,avg(gamePointId)from cube cube18")
    checkAnswer(
      sql("select ActiveCountry from cube18 limit 1"),
      Seq(Row("Chinese")))
    sql("drop cube cube18")
  }

  //TC_1289
  test("TC_1289") {
    sql("create cube cube19 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube19 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("CREATE AGGREGATETABLE ActiveCountry,max(deviceInformationId)FROM CUBE cube19")
    checkAnswer(
      sql("select imei from cube19 where imei=\"1AA1\""),
      Seq(Row("1AA1")))
    sql("drop cube cube19")
  }

  //DTS2015112608312
  test("DTS2015112608312") {
    sql("create cube cube21 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("CREATE AGGREGATETABLE ActiveCountry,max(deviceInformationId),sum(deviceInformationId) FROM CUBE cube21")
    checkAnswer(
      sql("select deviceInformationId from cube21 limit 1"),
      Seq())
    sql("drop cube cube21")
  }

  //DTS2015101505182
  test("DTS2015101505182") {
    sql("create cube cube22 dimensions(imei string, productdate timestamp) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("CREATE AGGREGATETABLE imei,productdate,max(gamePointId) FROM CUBE cube22")
    checkAnswer(
      sql("select productdate from cube22"),
      Seq())
    sql("drop cube cube22")
  }

  //DTS2015102211549
  test("DTS2015102211549") {
    sql("create cube cube23 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube23 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("CREATE AGGREGATETABLE imei,max(gamePointId) FROM CUBE cube23")
    checkAnswer(
      sql("select imei,max(gamePointId) FROM cube23 where imei=\"1AA10006\" group by imei"),
      Seq(Row("1AA10006",2478.0)))
    sql("drop cube cube23")
  }

  //DTS2015102309588_01
  test("DTS2015102309588_01") {
    sql("create cube cube24 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube24 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("CREATE AGGREGATETABLE imei,sum(distinct gamePointId) FROM CUBE cube24")
    checkAnswer(
      sql("select imei,sum(distinct gamePointId) FROM cube24 where imei=\"1AA10006\" group by imei limit 1"),
      Seq(Row("1AA10006",2478.0)))
    sql("drop cube cube24")
  }

  //DTS2015102309588_02
  test("DTS2015102309588_02") {
    sql("create cube cube25 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube25 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("CREATE AGGREGATETABLE imei,count(distinct gamePointId) FROM CUBE cube25")
    checkAnswer(
      sql("select count(imei),count(distinct gamePointId) FROM cube25 group by imei limit 1"),
      Seq(Row(1,1)))
    sql("drop cube cube25")
  }

  //DTS2015102309611
  test("DTS2015102309611") {
    sql("create cube cube26 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube26 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable ActiveCountry,avg(gamePointId)from cube cube26")
    checkAnswer(
      sql("select avg(gamePointId)from cube26  limit 1"),
      Seq(Row(1574.52)))
    sql("drop cube cube26")
  }

  //DTS2015102400015
  test("DTS2015102400015") {
    sql("create cube cube27 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube27 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable ActiveCountry,avg(gamePointId)from cube cube27")
    sql("drop cube cube27")
    sql("create cube cube27 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube27 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("create aggregatetable ActiveCountry,avg(gamePointId)from cube cube27")
    checkAnswer(
      sql("select avg(gamePointId)from cube27  limit 1"),
      Seq(Row(1574.52)))
    sql("drop cube cube27")
  }

  //DTS2015112707233
  test("DTS2015112707233") {
    sql("create cube cube28 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube28 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from cube cube28")
    sql("clean files for cube cube28")
    sql("CREATE AGGREGATETABLE imei,sum(distinct gamePointId) FROM CUBE cube28")
    checkAnswer(
      sql("select imei,sum(distinct gamePointId) FROM cube28 group by imei limit 1"),
      Seq())
    sql("drop cube cube28")
  }

  //DTS2015113008797_01
  test("DTS2015113008797_01") {
    sql("create cube cube29 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube29 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("CREATE AGGREGATETABLE imei,sum(distinct gamePointId) FROM CUBE cube29")
    checkAnswer(
      sql("select imei,deviceInformationId,sum(distinct gamePointId) FROM cube29 where imei=\"1AA100080\" group by imei,deviceInformationId"),
      Seq(Row("1AA100080",100080.0,954.0)))
    sql("drop cube cube29")
  }

  //DTS2015113008797_02
  test("DTS2015113008797_02") {
    sql("create cube cube30 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube30 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("CREATE AGGREGATETABLE IMEI,sum(distinct gamePOIntId) FROM CUBE cube30")
    checkAnswer(
      sql("select count(imei),count(deviceInformationId),count(distinct gamePointId) FROM cube30 group by imei,deviceInformationId limit 1"),
      Seq(Row(1,1,1)))
    sql("drop cube cube30")
  }

  //TC_1338
  test("TC_1338") {
    sql("create cube vardhanretention dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("alter cube vardhanretention drop(AMSize) add dimensions(AMSize string)")
    sql("CREATE AGGREGATETABLE AMSize,sum(gamePointId) FROM cube vardhanretention")
    checkAnswer(
      sql("select imei from  vardhanretention order by imei desc limit 1"),
      Seq(Row("1AA100084")))
    sql("drop cube vardhanretention")
  }



  //TC_1291
  test("TC_1291") {
    sql("create cube cube1 DIMENSIONS (imei string,deviceInformationId integer,MAC string,deviceColor string, device_backColor string,modelId string, marketName string, AMSize string, ROMSize string, CUPAudit string, CPIClocked string, series string, productionDate string, bomCode string, internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince  string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict  string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR  integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) MEASURES (gamePointId numeric,contractNumber numeric) OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO CUBE cube1 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("delete load 0 from  cube cube1")
    checkAnswer(
      sql("select imei from cube1"),
      Seq())
    sql("drop cube cube1")
  }

  //TC_1293
  test("TC_1293") {
    sql("create cube cube3 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube3 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  cube cube3")
    checkAnswer(
      sql("select gamePointId from cube3"),
      Seq())
    sql("drop cube cube3")
  }

  //TC_1294
  test("TC_1294") {
    sql("create cube myschema1.cube4 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube myschema1.cube4 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  cube myschema1.cube4")
    checkAnswer(
      sql("select channelsId from myschema1.cube4"),
      Seq())
    sql("drop cube myschema1.cube4")
  }

  //TC_1295
  test("TC_1295") {
    sql("create cube cube5 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei,AMSize) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube5 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from cube cube5")
    checkAnswer(
      sql("select AMSize from cube5"),
      Seq())
    sql("drop cube cube5")
  }

  //TC_1296
  test("TC_1296") {
    sql("create cube cube6 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube6 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  cube cube6")
    checkAnswer(
      sql("select deviceInformationId from cube6"),
      Seq())
    sql("drop cube cube6")
  }

  //TC_1297
  test("TC_1297") {
    sql("create cube cube7 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("delete load 0 from cube cube7")
    checkAnswer(
      sql("select AMSize from cube7"),
      Seq())
    sql("drop cube cube7")
  }

  //TC_1298
  test("TC_1298") {
    sql("create cube cube8 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("alter cube cube8 add dimensions(a1 string) measures (b1 integer)")
    sql("delete load 0 from  cube cube8")
    checkAnswer(
      sql("select a1,b1 from cube8"),
      Seq())
    sql("drop cube cube8")
  }

  //TC_1299
  test("TC_1299") {
    sql("create cube cube9 dimensions(AMSize STRING) measures(deviceInformationId integer) OPTIONS (AGGREGATION [deviceInformationId=count])")
    sql("alter cube cube9 add dimensions(a1 string) measures (b1 integer)")
    sql("alter cube cube9 drop(a1,b1)")
    sql("delete load 0 from cube cube9")
    checkAnswer(
      sql("select deviceInformationId from cube9"),
      Seq())
    sql("drop cube cube9")
  }

  //TC_1300
  test("TC_1300") {
    sql("create cube cube10 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube10 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube cube10 add dimensions(a1 string) measures (b1 integer)")
    sql("alter cube cube10 drop(a1,b1)")
    sql("delete load 1 from  cube cube10")
    checkAnswer(
      sql("select deviceInformationId from cube10 limit 1"),
      Seq(Row(1.0)))
    sql("drop cube cube10")
  }

  //TC_1301
  test("TC_1301") {
    sql("create cube cube11 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube11 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube cube11 add dimensions(a1 string) measures (b1 integer)")
    sql("alter cube cube11 drop(a1,b1)")
    sql("delete load 0 from  cube cube11")
    checkAnswer(
      sql("select AMSize from cube11 limit 1"),
      Seq())
    sql("drop cube cube11")
  }

  //TC_1302
  test("TC_1302") {
    sql("create cube cube12 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube12 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube12 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0,1 from  cube cube12")
    checkAnswer(
      sql("select gamePointId from cube12 limit 1"),
      Seq())
    sql("drop cube cube12")
  }

  //TC_1303
  test("TC_1303") {
    sql("create cube cube13 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube13 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube13 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  cube cube13")
    checkAnswer(
      sql("select imei from cube13 limit 1"),
      Seq(Row("1AA1")))
    sql("drop cube cube13")
  }

  //TC_1304
  test("TC_1304") {
    sql("create cube cube14 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube14 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("alter cube cube14 add dimensions(a1 string) measures (b1 integer)")
    sql("alter cube cube14 drop(a1,b1)")
    sql("delete load 0,1 from  cube cube14")
    checkAnswer(
      sql("select channelsId from cube14 limit 1"),
      Seq())
    sql("drop cube cube14")
  }

  //TC_1305
  test("TC_1305") {
    sql("create cube cube15 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("alter cube cube15 add dimensions(a1 string) measures(b1 integer)")
    sql("alter cube cube15 add dimensions(a2 string) measures(b2 integer)")
    sql("delete load 0 from  cube cube15")
    sql("delete load 1 from  cube cube15")
    checkAnswer(
      sql("select ActiveCountry from cube15 limit 1"),
      Seq())
    sql("drop cube cube15")
  }

  //TC_1306
  test("TC_1306") {
    sql("create cube cube16 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube16 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube16 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  cube cube16")
    sql("delete load 1 from  cube cube16")
    checkAnswer(
      sql("select gamePointId from cube16 limit 1"),
      Seq())
    sql("drop cube cube16")
  }

  //TC_1308
  test("TC_1308") {
    sql("create cube cube18 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube18 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0,1 from  cube cube18")
    checkAnswer(
      sql("select ActiveCountry from cube18 limit 1"),
      Seq())
    sql("drop cube cube18")
  }

  //TC_1309
  test("TC_1309") {
    sql("create cube cube19 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string)  measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' columns= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM  './src/test/resources/TestData1.csv' INTO Cube cube19 partitionData(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("DELETE LOAD 0 FROM  CUBE cube19")
    checkAnswer(
      sql("select deviceInformationId from cube19"),
      Seq())
    sql("drop cube cube19")
  }

  //TC_1311
  test("TC_1311") {
    sql("create cube vardhanretention dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0 from cube vardhanretention")
    checkAnswer(
      sql("select imei,deviceInformationId from vardhanretention"),
      Seq())
    sql("drop cube vardhanretention")
  }

  //TC_1312
  test("TC_1312") {
    sql("create cube vardhanretention1 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention1 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention1 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0 from cube vardhanretention1")
    checkAnswer(
      sql("select imei,deviceInformationId from vardhanretention1  where imei='1AA1'"),
      Seq(Row("1AA1",1.0)))
    sql("drop cube vardhanretention1")
  }

  //TC_1314
  test("TC_1314") {
    sql("create cube vardhanretention3 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention3 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention3 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0 from cube vardhanretention3")
    checkAnswer(
      sql("select imei,AMSize from vardhanretention3 where gamePointId=1407"),
      Seq(Row("1AA100051" , "3RAM size"),Row("1AA100061" , "0RAM size")))
    sql("drop cube vardhanretention3")
  }
  //TC_1316
  test("TC_1316") {
    sql("create cube vardhanretention5 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention5 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention5 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0,1 from cube vardhanretention5")
    checkAnswer(
      sql("select imei,deviceInformationId from vardhanretention5"),
      Seq())
    sql("drop cube vardhanretention5")
  }

  //TC_1318
  test("TC_1318") {
    sql("create cube vardhanretention6 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention6 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete from cube vardhanretention6 where productionDate before '2015-07-05 12:07:28'")
    checkAnswer(
      sql("select count(*)  from vardhanretention6"),
      Seq(Row(54)))
    sql("drop cube vardhanretention6")
  }

  //TC_1322
  test("TC_1322") {
    sql("create cube vardhanretention9 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention9 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete from cube vardhanretention9 where productionDate before '2015-10-06 12:07:28 '")
    checkAnswer(
      sql("select count(*) from vardhanretention9"),
      Seq(Row(3)))
    sql("drop cube vardhanretention9")
  }

  //DTS2015112610913_03
  test("DTS2015112610913_031") {
    sql("create CUBE cube_restructure60 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructuRE60 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"', FILEHEADER 'a0,b0')")
    sql("alter cube CUBE_restructuRE60 add dimensions(a12 string) measures(b11 integer)")
    sql("delete load 0 from cube CUBE_restructuRE60")
    checkAnswer(
      sql("select * from CUBE_restructuRE60 limit 1"),
      Seq())
    sql("drop cube cube_restructure60")
  }


  //DTS2015110209900
  test("DTS2015110209900") {
    sql("create CUBE cube_restructure63 DIMENSIONS (a0 STRING) MEASURES (b0 INTEGER) OPTIONS (AGGREGATION [b0 = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (a0) ,PARTITION_COUNT=1] )")
    sql("LOAD DATA FACT FROM './src/test/resources/restructure_cube.csv' INTO CUBE cube_restructure63 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"', FILEHEADER 'a0,b0')")
    sql("delete load 0 from cube cube_RESTRUCTURE63")
    checkAnswer(
      sql("select * from cube_restructure63 limit 1"),
      Seq())
    sql("drop cube cube_restructure63")
  }


  //DTS2015110209543
  test("DTS2015110209543") {
    sql("create cube vardhanretention13 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention13 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete from cube vardhanretention13 where productionDate before '2015-09-08 12:07:28'")
    sql("delete from cube vardhanretention13 where productionDate before '2000-09-08 12:07:28'")
    checkAnswer(
      sql("select count(*) from vardhanretention13"),
      Seq(Row(31)))
    sql("drop cube vardhanretention13")
  }

  //DTS2015101506341
  test("DTS2015101506341") {
    sql("create cube vardhanretention13 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention13 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate') \"")
    sql("delete from cube vardhanretention13 where productiondate before '2015-09-08 12:07:28'")
    checkAnswer(
      sql("select count(*) from vardhanretention13"),
      Seq())
    sql("drop cube vardhanretention13")
  }

  //DTS2015112611263
  test("DTS2015112611263") {
    sql("create cube makamraghuvardhan002 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube makamraghuvardhan002 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube makamraghuvardhan002 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube makamraghuvardhan002 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("delete load 0 from cube makamraghuvardhan002")
    checkAnswer(
      sql("select count(*) from makamraghuvardhan002 where series='8Series'"),
      Seq(Row(11)))
    sql("drop cube makamraghuvardhan002")
  }

  //csv doesnt exist
  /*//DTS2015121509729
test("DTS2015121509729") {
sql("create cube vardhanretention002 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention002 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
sql("alter cube vardhanretention002 add dimensions(deliveryDate string) measures(deliverycharge integer) options ()")
sql("alter cube vardhanretention002 drop (deliveryDate,deliverycharge)")
sql("alter cube vardhanretention002 add dimensions(deliveryDate timestamp) measures(deliverycharge integer) options ()")
sql("LOAD DATA FACT FROM './src/test/resources/vardhandaterestruct.csv' INTO CUBE vardhanretention002 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate,deliveryDate,deliverycharge')")
sql("\"delete from cube vardhanretention002 where deliveryDate before '2015-09-12 12:07:28'")
checkAnswer(
sql("select count(*) from vardhanretention002"),
Seq())
sql("drop cube vardhanretention002")
}*/

  //DTS2015122300770
  test("DTS2015122300770") {
    sql("create table test1(imei string,deviceInformationId int,mac string,productdate timestamp,updatetime timestamp,gamePointId double,contractNumber double) row format delimited fields terminated by ','")
    sql("SHOW CREATE CUBE t22 FACT FROM test1 INCLUDE (deviceInformationId), DIMENSION FROM table4:test1 RELATION (FACT.imei = imei) EXCLUDE (mac,deviceInformationId) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS= (imei), PARTITION_COUNT=5] )")
    sql("CREATE CUBE default.t22 DIMENSIONS (imei String, productdate Timestamp, updatetime Timestamp, gamepointid Numeric, contractnumber Numeric) MEASURES (deviceinformationid Integer) WITH table4 RELATION (FACT.imei=imei) INCLUDE (imei, productdate, updatetime, gamepointid, contractnumber) OPTIONS( PARTITIONER[ CLASS='org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl', COLUMNS=(imei), PARTITION_COUNT=5 ] )")
    sql("LOAD DATA FACT FROM './src/test/resources/test13.csv' DIMENSION FROM table4:'./src/test/resources/test14.csv' INTO CUBE t22 PARTITIONDATA (DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceinformationid')")
    sql("delete from cube t22 where productdate before '2015-04-01 10:00:00'")
    checkAnswer(
      sql("select count(*) from t22"),
      Seq())
    sql("drop cube test1(imei")
  }

  //TC_1339
  test("TC_1339") {
    sql("create cube vardhan323 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhan323 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete from cube vardhan323  where productiondate before '2015-08-07 12:07:28'")
    checkAnswer(
      sql("select productiondate from vardhan323 order by imei ASC limit 3"),
      Seq(Row(null,"2015-08-07 12:07:28.0","2015-08-08 12:07:28.0")))
    sql("drop cube vardhan323")
  }

  //TC_1340
  test("TC_1340") {
    sql("create cube vardhan60 dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhan60 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhan60 OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0 from cube vardhan60")
    sql("delete from cube vardhan60 where productionDate before '2015-07-25 12:07:28'")
    checkAnswer(
      sql("select count(*)from vardhan60"),
      Seq(Row(76)))
    sql("drop cube vardhan60")
  }

  //TC_1341
  test("TC_1341") {
    sql("create cube vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    sql("alter cube vardhan01 add dimensions(productiondate timestamp) options (AGGREGATION [b = SUM])")
    sql("delete load 0 from cube vardhan01")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq(Row(0)))
    sql("drop cube vardhan01")
  }

  //TC_1342
  test("TC_1342") {
    sql("create cube vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    sql("alter cube vardhan01 add dimensions(productiondate timestamp) options (AGGREGATION [b = SUM])")
    sql("delete from cube vardhan01 where productiondate before '2015-08-10 19:59:00'")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq(Row(21)))
    sql("drop cube vardhan01")
  }

  //TC_1343
  test("TC_1343") {
    sql("create cube vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    sql("alter cube vardhan01 add dimensions(productiondate timestamp) options (AGGREGATION [b = SUM])")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    sql("delete from cube vardhan01 where productiondate before '2015-01-10 19:59:00'")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq())
    sql("drop cube vardhan01")
  }

  //TC_1344
  test("TC_1344") {
    sql("create schema drug")
    sql("create cube vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("create cube drug.vardhan01 dimensions(key string,name string) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE drug.vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    sql("delete load 0 from cube vardhan01")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq())
    sql("drop cube drug")
  }

  //TC_1345
  test("TC_1345") {
    sql("create cube vardhan01 dimensions(key string,name string ,productiondate timestamp) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("create cube drug.vardhan01 dimensions(key string,name string, productiondate timestamp) measures(gamepointid numeric,price numeric) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    sql("LOAD DATA FACT FROM './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO CUBE drug.vardhan01 PARTITIONDATA(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER '')")
    sql("delete from cube vardhan01 where productiondate before '2015-01-12 00:00:00'")
    checkAnswer(
      sql("select count(*) from vardhan01"),
      Seq())
    sql("drop cube vardhan01")
  }

  //TC_1347
  test("TC_1347") {
    sql("create cube vardhanretention dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("alter cube vardhanretention drop (AMSize)")
    sql("delete from cube vardhanretention where productionDate before '2015-08-10 19:59:00'")
    checkAnswer(
      sql("select count(*) from vardhanretention"),
      Seq(Row(59)))
    sql("drop cube vardhanretention")
  }

  //TC_1349
  test("TC_1349") {
    sql("create cube vardhanretention dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("alter cube vardhanretention drop(AMSize) add dimensions(AMSize string)")
    sql("delete load 0 from cube vardhanretention")
    checkAnswer(
      sql("select imei,deviceInformationId from vardhanretention where imei='1AA100'"),
      Seq())
    sql("drop cube vardhanretention")
  }

  //TC_1350
  test("TC_1350") {
    sql("create cube vardhanretention dimensions(imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp) measures(gamePointId numeric,deviceInformationId integer) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,columns= (imei) ,PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/TestData3.csv' INTO CUBE vardhanretention OPTIONS(DELIMITER ',', QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("alter cube vardhanretention drop(AMSize) add dimensions(AMSize string)")
    sql("delete load 0 from cube vardhanretention")
    checkAnswer(
      sql("select count(*) from vardhanretention"),
      Seq(Row(0)))
    sql("drop cube vardhanretention")
  }


  //TC_1259
  test("TC_1259") {
    sql("CREATE DATABASE IF NOT EXISTS my")
    sql("create cube my.Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube my.Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("SUGGEST AGGREGATE WITH SCRIPTS USING DATA_STATS FOR cube my.Carbon01"),
      Seq(Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  Latest_province,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube my.Carbon01  ")))
    sql("drop cube my.Carbon01")
  }

  //TC_1260
  test("TC_1260") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("SUGGEST AGGREGATE WITH SCRIPTS USING DATA_STATS FOR cube Carbon01"),
      Seq(Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  Latest_province,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01")))
    sql("drop cube Carbon01")
  }

  //TC_1261
  test("TC_1261") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("SUGGEST AGGREGATE  USING DATA_STATS FOR cube Carbon01"),
      Seq(Row("DATA_STATS"," ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","Latest_province,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)")))
    sql("drop cube Carbon01")
  }

  //TC_1262
  test("TC_1262") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("")
    checkAnswer(
      sql("SUGGEST AGGREGATE  USING DATA_STATS FOR cube Carbon01"),
      Seq(Row("DATA_STATS"," ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","Latest_province,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)")))
    sql("drop cube Carbon01")
  }

  //TC_1263
  test("TC_1263") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("execute the query\"select ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) from Carbon01 group by ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber\"")
    checkAnswer(
      sql("SUGGEST AGGREGATE  WITH SCRIPTS USING QUERY_STATS FOR cube Carbon01"),
      Seq(Row("DATA_STATS","CREATE AGGREGATETABLE  MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveProvince,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) from cube Carbon01")))
    sql("drop cube Carbon01")
  }

  //TC_1264
  test("TC_1264") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("SUGGEST AGGREGATE  USING QUERY_STATS FOR cube Carbon01"),
      Seq(Row("DATA_STATS","MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveProvince,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber)")))
    sql("drop cube Carbon01")
  }

  //TC_1265
  test("TC_1265") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("SUGGEST AGGREGATE   FOR cube Carbon01"),
      Seq(Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryTime,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,productionDate,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,MAC,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","ActiveProvince,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","Latest_province,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber)"),Row("DATA_STATS","MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveProvince,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) ")))
    sql("drop cube Carbon01")
  }

  //TC_1266
  test("TC_1266") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("SUGGEST AGGREGATE with scripts  FOR cube Carbon01"),
      Seq(Row("DATA_STATS","   CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  Latest_province,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveProvince,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) from cube Carbon01 ")))
    sql("drop cube Carbon01")
  }

  //TC_1268
  test("TC_1268") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("Create AGGREGATETABLE imei,sum(gamePointId) FROM cube Carbon05"),
      Seq(Row("DATA_STATS","CREATE AGGREGATETABLE  imei,sum(gamepointid) From cube Carbon01")))
    sql("drop cube Carbon01")
  }

  //TC_1270
  test("TC_1270") {
    sql("create cube Carbon01 dimensions(imei string,deviceInformationId integer,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR integer, Latest_MONTH integer, Latest_DAY integer, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string) measures(gamePointId numeric,contractNumber numeric) OPTIONS (PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' ,COLUMNS= (imei) , PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/100.csv' INTO Cube Carbon01 OPTIONS(DELIMITER ',' ,QUOTECHAR '\"\"', FILEHEADER 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    checkAnswer(
      sql("SUGGEST AGGREGATE with scripts for cube Carbon01"),
      Seq(Row("DATA_STATS"," CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_operaSysVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_EMUIVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryTime,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,productionDate,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,MAC,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  ActiveProvince,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  Latest_province,MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveCountry,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_country,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_phonePADPartitionedVersions,sum(gamePointId),sum(contractNumber) from cube Carbon01"),Row("DATA_STATS","CREATE AGGREGATETABLE  MAC,productionDate,deliveryTime,deliveryCountry,ActiveCheckTime,ActiveProvince,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_phonePADPartitionedVersions,Latest_MONTH,Latest_DAY,Latest_EMUIVersion,Latest_BacVerNumber,sum(gamePointId),sum(contractNumber) from cube Carbon01 ")))
    sql("drop cube Carbon01")
  }

  //TC_1337
  test("TC_1337") {
    sql("create cube makam05 DIMENSIONS (imei String,uuid String,MAC String,device_color String,device_shell_color String,device_name String,product_name String,ram String,rom String,cpu_clock String,series String,check_date String,check_year String,check_month String,check_day String,check_hour String,bom String,inside_name String,packing_date String,packing_year String,packing_month String,packing_day String,packing_hour String,customer_name String,deliveryAreaId String,deliveryCountry String,deliveryProvince String,deliveryCity String,deliveryDistrict String,packing_list_no String,order_no String,Active_check_time String,Active_check_year String,Active_check_month String,Active_check_day String,Active_check_hour String,ActiveAreaId String,ActiveCountry String,ActiveProvince String,Activecity String,ActiveDistrict String,Active_network String,Active_firmware_version String,Active_emui_version String,Active_os_version String,Latest_check_time String,Latest_check_year String,Latest_check_month String,Latest_check_day String,Latest_check_hour String,Latest_areaId String,Latest_country String,Latest_province String,Latest_city String,Latest_district String,Latest_firmware_version String,Latest_emui_version String,Latest_os_version String,Latest_network String,site String,site_desc String,product String,product_desc String)  OPTIONS (AGGREGATION [Latest_Day = count] PARTITIONER [CLASS = 'org.carbondata.spark.partition.api.impl.SampleDataPartitionerImpl' COLUMNS= (imei) PARTITION_COUNT=2] )")
    sql("LOAD DATA FACT FROM './src/test/resources/bigdata.csv' INTO CUBE makam05 PARTITIONDATA(DELIMITER ',', QUOTECHAR  '\"\"', FILEHEADER 'imei,uuid,MAC,device_color,device_shell_color,device_name,product_name,ram,rom,cpu_clock,series,check_date,check_year,check_month,check_day,check_hour,bom,inside_name,packing_date,packing_year,packing_month,packing_day,packing_hour,customer_name,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,packing_list_no,order_no,Active_check_time,Active_check_year,Active_check_month,Active_check_day,Active_check_hour,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,Active_network,Active_firmware_version,Active_emui_version,Active_os_version,Latest_check_time,Latest_check_year,Latest_check_month,Latest_check_day,Latest_check_hour,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_firmware_version,Latest_emui_version,Latest_os_version,Latest_network,site,site_desc,product,product_desc')")
    sql("alter cube makam05 drop(uuid) add dimensions(uuid string)")
    checkAnswer(
      sql("suggest aggregate using data_stats for cube makam05"),
      Seq())
    sql("drop cube makam05")
  }

  //TestDataTypes5

  //TC_503
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou")))
  })

  //TC_504
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_505
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(760.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_506
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan")))
  })

  //TC_507
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize <= \"5RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize <= \"5RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan")))
  })

  //TC_509
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize, gamePointId ,deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({

    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize, gamePointId ,deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_509.csv")
  })

  //TC_510
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '5RAM %' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '5RAM %' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan")))
  })

  //TC_511
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize BETWEEN \"2RAM size\" AND \"6RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize BETWEEN \"2RAM size\" AND \"6RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou")))
  })

  //TC_512
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1691.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou")))
  })

  //TC_514
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize = \"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize = \"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_515
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize > \"6RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize > \"6RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou")))
  })

  //TC_516
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize >= \"5RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize >= \"5RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha",19558.0),Row(2194.0,"0RAM size","Chinese","changsha",24134.0),Row(1098.0,"0RAM size","Chinese","changsha",12078.0),Row(2593.0,"0RAM size","Chinese","changsha",28523.0),Row(79.0,"0RAM size","Chinese","guangzhou",869.0),Row(2849.0,"0RAM size","Chinese","shenzhen",31339.0),Row(750.0,"0RAM size","Chinese","wuhan",8250.0),Row(1407.0,"0RAM size","Chinese","wuhan",15477.0),Row(1442.0,"0RAM size","Chinese","wuhan",15862.0),Row(2483.0,"0RAM size","Chinese","wuhan",27313.0),Row(1341.0,"0RAM size","Chinese","zhuzhou",14751.0),Row(1333.0,"1RAM size","Chinese","guangzhou",11997.0),Row(256.0,"1RAM size","Chinese","shenzhen",2304.0),Row(2175.0,"1RAM size","Chinese","xiangtan",19575.0),Row(2734.0,"1RAM size","Chinese","xiangtan",24606.0),Row(202.0,"1RAM size","Chinese","xiangtan",1818.0),Row(2399.0,"1RAM size","Chinese","xiangtan",21591.0),Row(2078.0,"1RAM size","Chinese","yichang",18702.0),Row(1864.0,"1RAM size","Chinese","yichang",16776.0),Row(2745.0,"1RAM size","Chinese","zhuzhou",24705.0),Row(1973.0,"2RAM size","Chinese","changsha",3946.0),Row(1350.0,"2RAM size","Chinese","xiangtan",2700.0),Row(2863.0,"3RAM size","Chinese","changsha",40082.0),Row(1999.0,"3RAM size","Chinese","guangzhou",27986.0),Row(2192.0,"3RAM size","Chinese","shenzhen",30688.0),Row(907.0,"3RAM size","Chinese","shenzhen",12698.0),Row(1053.0,"3RAM size","Chinese","shenzhen",14742.0),Row(2488.0,"3RAM size","Chinese","shenzhen",34832.0),Row(2635.0,"3RAM size","Chinese","wuhan",36890.0),Row(1407.0,"3RAM size","Chinese","xiangtan",19698.0),Row(1337.0,"3RAM size","Chinese","xiangtan",18718.0),Row(1080.0,"3RAM size","Chinese","xiangtan",15120.0),Row(1491.0,"3RAM size","Chinese","yichang",20874.0),Row(1608.0,"3RAM size","Chinese","zhuzhou",22512.0),Row(1655.0,"3RAM size","Chinese","zhuzhou",23170.0),Row(2436.0,"3RAM size","Chinese","zhuzhou",34104.0),Row(1691.0,"4RAM size","Chinese","changsha",37202.0),Row(2288.0,"4RAM size","Chinese","changsha",50336.0),Row(2572.0,"4RAM size","Chinese","changsha",56584.0),Row(813.0,"4RAM size","Chinese","changsha",17886.0),Row(901.0,"4RAM size","Chinese","changsha",19822.0),Row(865.0,"4RAM size","Chinese","changsha",19030.0),Row(1728.0,"4RAM size","Chinese","guangzhou",38016.0),Row(538.0,"4RAM size","Chinese","shenzhen",11836.0),Row(1717.0,"4RAM size","Chinese","shenzhen",37774.0),Row(1077.0,"4RAM size","Chinese","wuhan",23694.0),Row(1714.635,"4RAM size","Chinese","wuhan",37721.96999999999),Row(2553.0,"4RAM size","Chinese","wuhan",56166.0),Row(1600.0,"4RAM size","Chinese","xiangtan",35200.0),Row(412.0,"4RAM size","Chinese","xiangtan",9064.0),Row(2890.0,"4RAM size","Chinese","xiangtan",63580.0),Row(2826.0,"4RAM size","Chinese","xiangtan",62172.0),Row(1991.0,"4RAM size","Chinese","xiangtan",43802.0),Row(1841.0,"4RAM size","Chinese","xiangtan",40502.0),Row(29.0,"4RAM size","Chinese","yichang",638.0),Row(441.0,"4RAM size","Chinese","yichang",9702.0),Row(136.0,"4RAM size","Chinese","yichang",2992.0),Row(732.0,"4RAM size","Chinese","yichang",16104.0)))
  })

  //TC_517
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize < \"4RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize < \"4RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1691.0,"4RAM size","Chinese","changsha",37202.0),Row(901.0,"4RAM size","Chinese","changsha",19822.0),Row(865.0,"4RAM size","Chinese","changsha",19030.0),Row(2572.0,"4RAM size","Chinese","changsha",56584.0),Row(2288.0,"4RAM size","Chinese","changsha",50336.0),Row(813.0,"4RAM size","Chinese","changsha",17886.0),Row(1728.0,"4RAM size","Chinese","guangzhou",38016.0),Row(1717.0,"4RAM size","Chinese","shenzhen",37774.0),Row(538.0,"4RAM size","Chinese","shenzhen",11836.0),Row(1077.0,"4RAM size","Chinese","wuhan",23694.0),Row(2553.0,"4RAM size","Chinese","wuhan",56166.0),Row(1714.635,"4RAM size","Chinese","wuhan",37721.96999999999),Row(1600.0,"4RAM size","Chinese","xiangtan",35200.0),Row(412.0,"4RAM size","Chinese","xiangtan",9064.0),Row(2890.0,"4RAM size","Chinese","xiangtan",63580.0),Row(1991.0,"4RAM size","Chinese","xiangtan",43802.0),Row(1841.0,"4RAM size","Chinese","xiangtan",40502.0),Row(2826.0,"4RAM size","Chinese","xiangtan",62172.0),Row(29.0,"4RAM size","Chinese","yichang",638.0),Row(441.0,"4RAM size","Chinese","yichang",9702.0),Row(136.0,"4RAM size","Chinese","yichang",2992.0),Row(732.0,"4RAM size","Chinese","yichang",16104.0),Row(2077.0,"5RAM size","Chinese","changsha",10385.0),Row(692.0,"5RAM size","Chinese","changsha",3460.0),Row(2507.0,"5RAM size","Chinese","guangzhou",12535.0),Row(2205.0,"5RAM size","Chinese","guangzhou",11025.0),Row(2478.0,"5RAM size","Chinese","wuhan",12390.0),Row(572.0,"6RAM size","Chinese","changsha",5148.0),Row(2061.0,"6RAM size","Chinese","changsha",18549.0),Row(1768.0,"6RAM size","Chinese","guangzhou",15912.0),Row(2142.0,"6RAM size","Chinese","shenzhen",19278.0),Row(1434.0,"6RAM size","Chinese","wuhan",12906.0),Row(1823.0,"6RAM size","Chinese","wuhan",16407.0),Row(568.0,"6RAM size","Chinese","xiangtan",5112.0),Row(298.0,"6RAM size","Chinese","xiangtan",2682.0),Row(2952.0,"6RAM size","Chinese","zhuzhou",26568.0),Row(151.0,"7RAM size","Chinese","changsha",1057.0),Row(1750.0,"7RAM size","Chinese","wuhan",12250.0),Row(1724.0,"7RAM size","Chinese","wuhan",12068.0),Row(505.0,"7RAM size","Chinese","wuhan",3535.0),Row(760.0,"7RAM size","Chinese","yichang",5320.0),Row(1271.0,"7RAM size","Chinese","yichang",8897.0),Row(2239.0,"7RAM size","Chinese","zhuzhou",15673.0),Row(2738.562,"8RAM size","Chinese","guangzhou",27385.619999999995),Row(355.0,"8RAM size","Chinese","shenzhen",3550.0),Row(2970.0,"8RAM size","Chinese","wuhan",29700.0),Row(1873.0,"8RAM size","Chinese","xiangtan",18730.0),Row(1229.0,"8RAM size","Chinese","xiangtan",12290.0),Row(2972.0,"8RAM size","Chinese","yichang",29720.0),Row(2194.0,"8RAM size","Chinese","yichang",21940.0),Row(845.0,"8RAM size","Chinese","zhuzhou",8450.0),Row(613.0,"8RAM size","Chinese","zhuzhou",6130.0),Row(1226.0,"8RAM size","Chinese","zhuzhou",12260.0),Row(2224.0,"9RAM size","Chinese","changsha",22240.0),Row(1015.0,"9RAM size","Chinese","changsha",10150.0),Row(1697.0,"9RAM size","Chinese","shenzhen",16970.0),Row(1368.0,"9RAM size","Chinese","shenzhen",13680.0),Row(1567.0,"9RAM size","Chinese","wuhan",15670.0),Row(448.0,"9RAM size","Chinese","xiangtan",4480.0),Row(2071.0,"9RAM size","Chinese","xiangtan",20710.0),Row(2348.0,"9RAM size","Chinese","xiangtan",23480.0),Row(954.0,"9RAM size","Chinese","xiangtan",9540.0),Row(571.0,"9RAM size","Chinese","yichang",5710.0)))
  })

  //TC_520
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, gamePointId,Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, gamePointId,Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(1973.0,"Chinese","2RAM size","changsha"),Row(1973.0,"Chinese","2RAM size","changsha"),Row(1350.0,"Chinese","2RAM size","xiangtan"),Row(1350.0,"Chinese","2RAM size","xiangtan"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese"
        ,"4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang")))
  })

  //TC_525
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity,gamePointId, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity,gamePointId, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize"),"TC_525.csv")})

  //TC_526
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId ASC"),"TC_526.csv")
  })

  //TC_527
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT  AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT  AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId DESC"),"TC_527.csv")
  })

  //TC_532
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC 1") ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_532.csv")
  })


  //TC_535
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize,deviceInformationId,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize,deviceInformationId,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId ASC"),"TC_535.csv")
  })

  //TC_536
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry, deviceInformationId,gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry, deviceInformationId,gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId DESC"),"TC_536.csv")
  })

  //TC_541
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_541.csv")
  })

  //TC_544
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.gamePointId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.gamePointId DESC"),"TC_544.csv")
  })

  //TC_545
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, deviceInformationId,gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, deviceInformationId,gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC"),"TC_545.csv")
  })

  //TC_546
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC"),"TC_546.csv")
  })

  //TC_548
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize,gamePointId, ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize,gamePointId, ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_548.csv")
  })


  //TC_551
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId, First(Carbon_automation_test5.deviceInformationId) AS First_deviceInformationId FROM ( SELECT AMSize,deviceInformationId, gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId, First(Carbon_automation_test5.deviceInformationId) AS First_deviceInformationId FROM ( SELECT AMSize,deviceInformationId, gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row("0RAM size","Chinese","changsha",84293.0,100079),Row("0RAM size","Chinese","guangzhou",869.0,100010),Row("0RAM size","Chinese","shenzhen",31339.0,100028),Row("0RAM size","Chinese","wuhan",66902.0,100008),Row("0RAM size","Chinese","zhuzhou",14751.0,100002),Row("1RAM size","Chinese","guangzhou",11997.0,100030),Row("1RAM size","Chinese","shenzhen",2304.0,100020),Row("1RAM size","Chinese","xiangtan",67590.0,10000),Row("1RAM size","Chinese","yichang",35478.0,100040),Row("1RAM size","Chinese","zhuzhou",24705.0,100042),Row("2RAM size","Chinese","changsha",3946.0,100071),Row("2RAM size","Chinese","xiangtan",2700.0,10007),Row("3RAM size","Chinese","changsha",40082.0,100015),Row("3RAM size","Chinese","guangzhou",27986.0,100022),Row("3RAM size","Chinese","shenzhen",92960.0,100073),Row("3RAM size","Chinese","wuhan",36890.0,100058),Row("3RAM size","Chinese","xiangtan",53536.0,100031),Row("3RAM size","Chinese","yichang",20874.0,100069),Row("3RAM size","Chinese","zhuzhou",79786.0,100027),Row("4RAM size","Chinese","changsha",200860.0,100057),Row("4RAM size","Chinese","guangzhou",38016.0,100055),Row("4RAM size","Chinese","shenzhen",49610.0,100060),Row("4RAM size","Chinese","wuhan",117581.96999999999,100045),Row("4RAM size","Chinese","xiangtan",254320.0,100049),Row("4RAM size","Chinese","yichang",29436.0,100000),Row("5RAM size","Chinese","changsha",13845.0,100077),Row("5RAM size","Chinese","guangzhou",23560.0,100075),Row("5RAM size","Chinese","wuhan",12390.0,10006),Row("6RAM size","Chinese","changsha",23697.0,100034),Row("6RAM size","Chinese","guangzhou",15912.0,100026),Row("6RAM size","Chinese","shenzhen",19278.0,100035),Row("6RAM size","Chinese","wuhan",29313.0,100047),Row("6RAM size","Chinese","xiangtan",7794.0,10001),Row("6RAM size","Chinese","zhuzhou",26568.0,100062),Row("7RAM size","Chinese","changsha",1057.0,100014),Row("7RAM size","Chinese","wuhan",27853.0,100001),Row("7RAM size","Chinese","yichang",14217.0,100),Row("7RAM size","Chinese","zhuzhou",15673.0,100003),Row("8RAM size","Chinese","guangzhou",27385.619999999995,1),Row("8RAM size","Chinese","shenzhen",3550.0,100013),Row("8RAM size","Chinese","wuhan",29700.0,100004),Row("8RAM size","Chinese","xiangtan",31020.0,100016),Row("8RAM size","Chinese","yichang",51660.0,10002),Row("8RAM size","Chinese","zhuzhou",26840.0,100052),Row("9RAM size","Chinese","changsha",32390.0,100036),Row("9RAM size","Chinese","shenzhen",30650.0,100044),Row("9RAM size","Chinese","wuhan",15670.0,100070),Row("9RAM size","Chinese","xiangtan",58210.0,100072),Row("9RAM size","Chinese","yichang",5710.0,100043)))
  })

  //TC_552
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.deviceInformationId) AS Sum_deviceInformationId, First(Carbon_automation_test5.gamePointId) AS First_gamePointId FROM ( SELECT AMSize,gamePointId ,deviceInformationId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.deviceInformationId) AS Sum_deviceInformationId, First(Carbon_automation_test5.gamePointId) AS First_gamePointId FROM ( SELECT AMSize,gamePointId ,deviceInformationId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row("0RAM size","Chinese","changsha",4401364,2593.0),Row("0RAM size","Chinese","guangzhou",1100110,79.0),Row("0RAM size","Chinese","shenzhen",1100308,2849.0),Row("0RAM size","Chinese","wuhan",4401639,1442.0),Row("0RAM size","Chinese","zhuzhou",1100022,1341.0),Row("1RAM size","Chinese","guangzhou",900270,1333.0),Row("1RAM size","Chinese","shenzhen",900180,256.0),Row("1RAM size","Chinese","xiangtan",2790900,2175.0),Row("1RAM size","Chinese","yichang",1800954,2078.0),Row("1RAM size","Chinese","zhuzhou",900378,2745.0),Row("2RAM size","Chinese","changsha",200142,1973.0),Row("2RAM size","Chinese","xiangtan",20014,1350.0),Row("3RAM size","Chinese","changsha",1400210,2863.0),Row("3RAM size","Chinese","guangzhou",1400308,1999.0),Row("3RAM size","Chinese","shenzhen",5603668,1053.0),Row("3RAM size","Chinese","wuhan",1400812,2635.0),Row("3RAM size","Chinese","xiangtan",4201974,1080.0),Row("3RAM size","Chinese","yichang",1400966,1491.0),Row("3RAM size","Chinese","zhuzhou",2941190,2436.0),Row("4RAM size","Chinese","changsha",11225038,2572.0),Row("4RAM size","Chinese","guangzhou",2201210,1728.0),Row("4RAM size","Chinese","shenzhen",2421408,1717.0),Row("4RAM size","Chinese","wuhan",4402222,2553.0),Row("4RAM size","Chinese","xiangtan",33004774,1600.0),Row("4RAM size","Chinese","yichang",8803168,29.0),Row("5RAM size","Chinese","changsha",505385,2077.0),Row("5RAM size","Chinese","guangzhou",1000460,2507.0),Row("5RAM size","Chinese","wuhan",50030,2478.0),Row("6RAM size","Chinese","changsha",1800909,2061.0),Row("6RAM size","Chinese","guangzhou",900234,1768.0),Row("6RAM size","Chinese","shenzhen",900315,2142.0),Row("6RAM size","Chinese","wuhan",1801125,1823.0),Row("6RAM size","Chinese","xiangtan",990117,298.0),Row("6RAM size","Chinese","zhuzhou",900558,2952.0),Row("7RAM size","Chinese","changsha",700098,151.0),Row("7RAM size","Chinese","wuhan",2100455,505.0),Row("7RAM size","Chinese","yichang",700931,1271.0),Row("7RAM size","Chinese","zhuzhou",700021,2239.0),Row("8RAM size","Chinese","guangzhou",10,2738.562),Row("8RAM size","Chinese","shenzhen",1000130,355.0),Row("8RAM size","Chinese","wuhan",1000040,2970.0),Row("8RAM size","Chinese","xiangtan",2000540,1873.0),Row("8RAM size","Chinese","yichang",1100250,2972.0),Row("8RAM size","Chinese","zhuzhou",3001960,845.0),Row("9RAM size","Chinese","changsha",2000730,2224.0),Row("9RAM size","Chinese","shenzhen",2000980,1697.0),Row("9RAM size","Chinese","wuhan",1000700,1567.0),Row("9RAM size","Chinese","xiangtan",3102370,2071.0),Row("9RAM size","Chinese","yichang",1000430,571.0)))
  })

  //TC_553
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou")))
  })

  //TC_554
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_555
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(760.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_556
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan")))
  })

  //TC_557
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize <= \"5RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize <= \"5RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan")))
  })

  //TC_558
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '%1%' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5. gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '%1%' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5. gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou")))
  })

  //TC_559
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize, gamePointId ,deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize, gamePointId ,deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_559.csv")
  })

  //TC_560
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '5RAM %' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '5RAM %' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan")))
  })

  //TC_561
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize BETWEEN \"2RAM size\" AND \"6RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize BETWEEN \"2RAM size\" AND \"6RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou")))
  })

  //TC_562
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou")))
  })

  //TC_564
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize = \"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize = \"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_565
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize > \"6RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize > \"6RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou")))
  })

  //TC_566
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize >= \"5RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize >= \"5RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha",19558.0),Row(1098.0,"0RAM size","Chinese","changsha",12078.0),Row(2194.0,"0RAM size","Chinese","changsha",24134.0),Row(2593.0,"0RAM size","Chinese","changsha",28523.0),Row(79.0,"0RAM size","Chinese","guangzhou",869.0),Row(2849.0,"0RAM size","Chinese","shenzhen",31339.0),Row(1442.0,"0RAM size","Chinese","wuhan",15862.0),Row(1407.0,"0RAM size","Chinese","wuhan",15477.0),Row(2483.0,"0RAM size","Chinese","wuhan",27313.0),Row(750.0,"0RAM size","Chinese","wuhan",8250.0),Row(1341.0,"0RAM size","Chinese","zhuzhou",14751.0),Row(1333.0,"1RAM size","Chinese","guangzhou",11997.0),Row(256.0,"1RAM size","Chinese","shenzhen",2304.0),Row(2175.0,"1RAM size","Chinese","xiangtan",19575.0),Row(202.0,"1RAM size","Chinese","xiangtan",1818.0),Row(2399.0,"1RAM size","Chinese","xiangtan",21591.0),Row(2734.0,"1RAM size","Chinese","xiangtan",24606.0),Row(2078.0,"1RAM size","Chinese","yichang",18702.0),Row(1864.0,"1RAM size","Chinese","yichang",16776.0),Row(2745.0,"1RAM size","Chinese","zhuzhou",24705.0),Row(1973.0,"2RAM size","Chinese","changsha",3946.0),Row(1350.0,"2RAM size","Chinese","xiangtan",2700.0),Row(2863.0,"3RAM size","Chinese","changsha",40082.0),Row(1999.0,"3RAM size","Chinese","guangzhou",27986.0),Row(907.0,"3RAM size","Chinese","shenzhen",12698.0),Row(1053.0,"3RAM size","Chinese","shenzhen",14742.0),Row(2488.0,"3RAM size","Chinese","shenzhen",34832.0),Row(2192.0,"3RAM size","Chinese","shenzhen",30688.0),Row(2635.0,"3RAM size","Chinese","wuhan",36890.0),Row(1337.0,"3RAM size","Chinese","xiangtan",18718.0),Row(1407.0,"3RAM size","Chinese","xiangtan",19698.0),Row(1080.0,"3RAM size","Chinese","xiangtan",15120.0),Row(1491.0,"3RAM size","Chinese","yichang",20874.0),Row(2436.0,"3RAM size","Chinese","zhuzhou",34104.0),Row(1655.0,"3RAM size","Chinese","zhuzhou",23170.0),Row(1608.0,"3RAM size","Chinese","zhuzhou",22512.0),Row(1691.0,"4RAM size","Chinese","changsha",37202.0),Row(813.0,"4RAM size","Chinese","changsha",17886.0),Row(901.0,"4RAM size","Chinese","changsha",19822.0),Row(865.0,"4RAM size","Chinese","changsha",19030.0),Row(2288.0,"4RAM size","Chinese","changsha",50336.0),Row(2572.0,"4RAM size","Chinese","changsha",56584.0),Row(1728.0,"4RAM size","Chinese","guangzhou",38016.0),Row(538.0,"4RAM size","Chinese","shenzhen",11836.0),Row(1717.0,"4RAM size","Chinese","shenzhen",37774.0),Row(1077.0,"4RAM size","Chinese","wuhan",23694.0),Row(1714.635,"4RAM size","Chinese","wuhan",37721.96999999999),Row(2553.0,"4RAM size","Chinese","wuhan",56166.0),Row(1991.0,"4RAM size","Chinese","xiangtan",43802.0),Row(1841.0,"4RAM size","Chinese","xiangtan",40502.0),Row(1600.0,"4RAM size","Chinese","xiangtan",35200.0),Row(412.0,"4RAM size","Chinese","xiangtan",9064.0),Row(2890.0,"4RAM size","Chinese","xiangtan",63580.0),Row(2826.0,"4RAM size","Chinese","xiangtan",62172.0),Row(441.0,"4RAM size","Chinese","yichang",9702.0),Row(136.0,"4RAM size","Chinese","yichang",2992.0),Row(29.0,"4RAM size","Chinese","yichang",638.0),Row(732.0,"4RAM size","Chinese","yichang",16104.0)))
  })

  //TC_567
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize < \"4RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize < \"4RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2288.0,"4RAM size","Chinese","changsha",50336.0),Row(2572.0,"4RAM size","Chinese","changsha",56584.0),Row(813.0,"4RAM size","Chinese","changsha",17886.0),Row(1691.0,"4RAM size","Chinese","changsha",37202.0),Row(901.0,"4RAM size","Chinese","changsha",19822.0),Row(865.0,"4RAM size","Chinese","changsha",19030.0),Row(1728.0,"4RAM size","Chinese","guangzhou",38016.0),Row(538.0,"4RAM size","Chinese","shenzhen",11836.0),Row(1717.0,"4RAM size","Chinese","shenzhen",37774.0),Row(1077.0,"4RAM size","Chinese","wuhan",23694.0),Row(1714.635,"4RAM size","Chinese","wuhan",37721.96999999999),Row(2553.0,"4RAM size","Chinese","wuhan",56166.0),Row(1991.0,"4RAM size","Chinese","xiangtan",43802.0),Row(1841.0,"4RAM size","Chinese","xiangtan",40502.0),Row(2826.0,"4RAM size","Chinese","xiangtan",62172.0),Row(1600.0,"4RAM size","Chinese","xiangtan",35200.0),Row(412.0,"4RAM size","Chinese","xiangtan",9064.0),Row(2890.0,"4RAM size","Chinese","xiangtan",63580.0),Row(29.0,"4RAM size","Chinese","yichang",638.0),Row(732.0,"4RAM size","Chinese","yichang",16104.0),Row(441.0,"4RAM size","Chinese","yichang",9702.0),Row(136.0,"4RAM size","Chinese","yichang",2992.0),Row(692.0,"5RAM size","Chinese","changsha",3460.0),Row(2077.0,"5RAM size","Chinese","changsha",10385.0),Row(2507.0,"5RAM size","Chinese","guangzhou",12535.0),Row(2205.0,"5RAM size","Chinese","guangzhou",11025.0),Row(2478.0,"5RAM size","Chinese","wuhan",12390.0),Row(572.0,"6RAM size","Chinese","changsha",5148.0),Row(2061.0,"6RAM size","Chinese","changsha",18549.0),Row(1768.0,"6RAM size","Chinese","guangzhou",15912.0),Row(2142.0,"6RAM size","Chinese","shenzhen",19278.0),Row(1434.0,"6RAM size","Chinese","wuhan",12906.0),Row(1823.0,"6RAM size","Chinese","wuhan",16407.0),Row(298.0,"6RAM size","Chinese","xiangtan",2682.0),Row(568.0,"6RAM size","Chinese","xiangtan",5112.0),Row(2952.0,"6RAM size","Chinese","zhuzhou",26568.0),Row(151.0,"7RAM size","Chinese","changsha",1057.0),Row(505.0,"7RAM size","Chinese","wuhan",3535.0),Row(1750.0,"7RAM size","Chinese","wuhan",12250.0),Row(1724.0,"7RAM size","Chinese","wuhan",12068.0),Row(760.0,"7RAM size","Chinese","yichang",5320.0),Row(1271.0,"7RAM size","Chinese","yichang",8897.0),Row(2239.0,"7RAM size","Chinese","zhuzhou",15673.0),Row(2738.562,"8RAM size","Chinese","guangzhou",27385.619999999995),Row(355.0,"8RAM size","Chinese","shenzhen",3550.0),Row(2970.0,"8RAM size","Chinese","wuhan",29700.0),Row(1873.0,"8RAM size","Chinese","xiangtan",18730.0),Row(1229.0,"8RAM size","Chinese","xiangtan",12290.0),Row(2194.0,"8RAM size","Chinese","yichang",21940.0),Row(2972.0,"8RAM size","Chinese","yichang",29720.0),Row(1226.0,"8RAM size","Chinese","zhuzhou",12260.0),Row(845.0,"8RAM size","Chinese","zhuzhou",8450.0),Row(613.0,"8RAM size","Chinese","zhuzhou",6130.0),Row(2224.0,"9RAM size","Chinese","changsha",22240.0),Row(1015.0,"9RAM size","Chinese","changsha",10150.0),Row(1368.0,"9RAM size","Chinese","shenzhen",13680.0),Row(1697.0,"9RAM size","Chinese","shenzhen",16970.0),Row(1567.0,"9RAM size","Chinese","wuhan",15670.0),Row(448.0,"9RAM size","Chinese","xiangtan",4480.0),Row(2071.0,"9RAM size","Chinese","xiangtan",20710.0),Row(2348.0,"9RAM size","Chinese","xiangtan",23480.0),Row(954.0,"9RAM size","Chinese","xiangtan",9540.0),Row(571.0,"9RAM size","Chinese","yichang",5710.0)))
  })


  //TC_570
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, gamePointId,Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, gamePointId,Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(1973.0,"Chinese","2RAM size","changsha"),Row(1973.0,"Chinese","2RAM size","changsha"),Row(1350.0,"Chinese","2RAM size","xiangtan"),Row(1350.0,"Chinese","2RAM size","xiangtan"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),
        Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang")))
  })


  //TC_575
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity,gamePointId, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity,gamePointId, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize"),"TC_575.csv")
  })

  //TC_576
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId ASC"),"TC_576.csv")
  })

  //TC_577
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT  AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT  AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId DESC"),"TC_577.csv")
  })

  //TC_582
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC1") ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_582.csv")
  })

  //TC_585
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize,deviceInformationId,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize,deviceInformationId,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId ASC"),"TC_585.csv")
  })

  //TC_586
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry, deviceInformationId,gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry, deviceInformationId,gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId DESC"),"TC_586.csv")
  })


  //TC_591
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_591.csv")
  })


  //TC_594
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.gamePointId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.gamePointId DESC"),"TC_594.csv")
  })

  //TC_595
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, deviceInformationId,gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, deviceInformationId,gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC"),"TC_595.csv")
  })

  //TC_596
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC") ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC"),"TC_596.csv")
  })


  //TC_598
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize,gamePointId, ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize,gamePointId, ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_598.csv")
  })

  //TC_601
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId, First(Carbon_automation_test5.deviceInformationId) AS First_deviceInformationId FROM ( SELECT AMSize,deviceInformationId, gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 Left join ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId, First(Carbon_automation_test5.deviceInformationId) AS First_deviceInformationId FROM ( SELECT AMSize,deviceInformationId, gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 Left join ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row("0RAM size","Chinese","changsha",84293.0,100005),Row("0RAM size","Chinese","guangzhou",869.0,100010),Row("0RAM size","Chinese","shenzhen",31339.0,100028),Row("0RAM size","Chinese","wuhan",66902.0,100056),Row("0RAM size","Chinese","zhuzhou",14751.0,100002),Row("1RAM size","Chinese","guangzhou",11997.0,100030),Row("1RAM size","Chinese","shenzhen",2304.0,100020),Row("1RAM size","Chinese","xiangtan",67590.0,100041),Row("1RAM size","Chinese","yichang",35478.0,100040),Row("1RAM size","Chinese","zhuzhou",24705.0,100042),Row("2RAM size","Chinese","changsha",3946.0,100071),Row("2RAM size","Chinese","xiangtan",2700.0,10007),Row("3RAM size","Chinese","changsha",40082.0,100015),Row("3RAM size","Chinese","guangzhou",27986.0,100022),Row("3RAM size","Chinese","shenzhen",92960.0,100073),Row("3RAM size","Chinese","wuhan",36890.0,100058),Row("3RAM size","Chinese","xiangtan",53536.0,100051),Row("3RAM size","Chinese","yichang",20874.0,100069),Row("3RAM size","Chinese","zhuzhou",79786.0,100027),Row("4RAM size","Chinese","changsha",200860.0,100006),Row("4RAM size","Chinese","guangzhou",38016.0,100055),Row("4RAM size","Chinese","shenzhen",49610.0,100060),Row("4RAM size","Chinese","wuhan",117581.96999999999,100045),Row("4RAM size","Chinese","xiangtan",254320.0,1000000),Row("4RAM size","Chinese","yichang",29436.0,100050),Row("5RAM size","Chinese","changsha",13845.0,1000),Row("5RAM size","Chinese","guangzhou",23560.0,100075),Row("5RAM size","Chinese","wuhan",12390.0,10006),Row("6RAM size","Chinese","changsha",23697.0,100067),Row("6RAM size","Chinese","guangzhou",15912.0,100026),Row("6RAM size","Chinese","shenzhen",19278.0,100035),Row("6RAM size","Chinese","wuhan",29313.0,100047),Row("6RAM size","Chinese","xiangtan",7794.0,10001),Row("6RAM size","Chinese","zhuzhou",26568.0,100062),Row("7RAM size","Chinese","changsha",1057.0,100014),Row("7RAM size","Chinese","wuhan",27853.0,100001),Row("7RAM size","Chinese","yichang",14217.0,100),Row("7RAM size","Chinese","zhuzhou",15673.0,100003),Row("8RAM size","Chinese","guangzhou",27385.619999999995,1),Row("8RAM size","Chinese","shenzhen",3550.0,100013),Row("8RAM size","Chinese","wuhan",29700.0,100004),Row("8RAM size","Chinese","xiangtan",31020.0,100016),Row("8RAM size","Chinese","yichang",51660.0,10002),Row("8RAM size","Chinese","zhuzhou",26840.0,100052),Row("9RAM size","Chinese","changsha",32390.0,100036),Row("9RAM size","Chinese","shenzhen",30650.0,100044),Row("9RAM size","Chinese","wuhan",15670.0,100070),Row("9RAM size","Chinese","xiangtan",58210.0,10003),Row("9RAM size","Chinese","yichang",5710.0,100043)))
  })

  //TC_602
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.deviceInformationId) AS Sum_deviceInformationId, First(Carbon_automation_test5.gamePointId) AS First_gamePointId FROM ( SELECT AMSize,gamePointId ,deviceInformationId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 Left join ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.deviceInformationId) AS Sum_deviceInformationId, First(Carbon_automation_test5.gamePointId) AS First_gamePointId FROM ( SELECT AMSize,gamePointId ,deviceInformationId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 Left join ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row("0RAM size","Chinese","changsha",4401364,1098.0),Row("0RAM size","Chinese","guangzhou",1100110,79.0),Row("0RAM size","Chinese","shenzhen",1100308,2849.0),Row("0RAM size","Chinese","wuhan",4401639,750.0),Row("0RAM size","Chinese","zhuzhou",1100022,1341.0),Row("1RAM size","Chinese","guangzhou",900270,1333.0),Row("1RAM size","Chinese","shenzhen",900180,256.0),Row("1RAM size","Chinese","xiangtan",2790900,2734.0),Row("1RAM size","Chinese","yichang",1800954,2078.0),Row("1RAM size","Chinese","zhuzhou",900378,2745.0),Row("2RAM size","Chinese","changsha",200142,1973.0),Row("2RAM size","Chinese","xiangtan",20014,1350.0),Row("3RAM size","Chinese","changsha",1400210,2863.0),Row("3RAM size","Chinese","guangzhou",1400308,1999.0),Row("3RAM size","Chinese","shenzhen",5603668,2488.0),Row("3RAM size","Chinese","wuhan",1400812,2635.0),Row("3RAM size","Chinese","xiangtan",4201974,1407.0),Row("3RAM size","Chinese","yichang",1400966,1491.0),Row("3RAM size","Chinese","zhuzhou",2941190,1608.0),Row("4RAM size","Chinese","changsha",11225038,2288.0),Row("4RAM size","Chinese","guangzhou",2201210,1728.0),Row("4RAM size","Chinese","shenzhen",2421408,1717.0),Row("4RAM size","Chinese","wuhan",4402222,1714.635),Row("4RAM size","Chinese","xiangtan",33004774,2890.0),Row("4RAM size","Chinese","yichang",8803168,136.0),Row("5RAM size","Chinese","changsha",505385,2077.0),Row("5RAM size","Chinese","guangzhou",1000460,2205.0),Row("5RAM size","Chinese","wuhan",50030,2478.0),Row("6RAM size","Chinese","changsha",1800909,2061.0),Row("6RAM size","Chinese","guangzhou",900234,1768.0),Row("6RAM size","Chinese","shenzhen",900315,2142.0),Row("6RAM size","Chinese","wuhan",1801125,1823.0),Row("6RAM size","Chinese","xiangtan",990117,298.0),Row("6RAM size","Chinese","zhuzhou",900558,2952.0),Row("7RAM size","Chinese","changsha",700098,151.0),Row("7RAM size","Chinese","wuhan",2100455,505.0),Row("7RAM size","Chinese","yichang",700931,1271.0),Row("7RAM size","Chinese","zhuzhou",700021,2239.0),Row("8RAM size","Chinese","guangzhou",10,2738.562),Row("8RAM size","Chinese","shenzhen",1000130,355.0),Row("8RAM size","Chinese","wuhan",1000040,2970.0),Row("8RAM size","Chinese","xiangtan",2000540,1873.0),Row("8RAM size","Chinese","yichang",1100250,2972.0),Row("8RAM size","Chinese","zhuzhou",3001960,845.0),Row("9RAM size","Chinese","changsha",2000730,2224.0),Row("9RAM size","Chinese","shenzhen",2000980,1697.0),Row("9RAM size","Chinese","wuhan",1000700,1567.0),Row("9RAM size","Chinese","xiangtan",3102370,448.0),Row("9RAM size","Chinese","yichang",1000430,571.0)))
  })

  //TC_603
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou")))
  })

  //TC_604
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_605
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(760.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_606
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC") ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan")))
  })

  //Test-16
  test("select imei, Latest_DAY+ 10 as a  from Carbon_automation_test5") {
    validateResult(sql("select imei, Latest_DAY+ 10 as a  from Carbon_automation_test5"),"TC_016.csv");
  }
  //Test-17
  test("select imei, gamePointId+ 10 as Total from Carbon_automation_test5") {
    validateResult(sql("select imei, gamePointId+ 10 as Total from Carbon_automation_test5"),"TC_017.csv");
  }
  //Test-18
  test("select imei, modelId+ 10 Total from Carbon_automation_test5 ") {
    validateResult(sql("select imei, modelId+ 10 Total from Carbon_automation_test5 "),"TC_018.csv");
  }
  //Test-19

  test("select imei, gamePointId+contractNumber as a  from Carbon_automation_test5 ") {
    validateResult(sql("select imei, gamePointId+contractNumber as a  from Carbon_automation_test5 "),"TC_019.csv");
  }

  //Test-20
  test("select imei, deviceInformationId+gamePointId as Total from Carbon_automation_test5") {
    validateResult(sql("select imei, deviceInformationId+gamePointId as Total from Carbon_automation_test5"),"TC_020.csv");
  }
  //Test-21
  test("select imei, deviceInformationId+deviceInformationId Total from Carbon_automation_test5") {
    validateResult(sql("select imei, deviceInformationId+deviceInformationId Total from Carbon_automation_test5"),"TC_021.csv");
  }

  //TC_477
  test("select percentile(1,array(1)) from Carbon_automation_test5") ({

    validateResult(sql("select percentile(1,array(1)) from Carbon_automation_test5"),"TC_477.csv");

  })


  //TC_479
  test("select percentile(1,array('0.5')) from Carbon_automation_test5") ({
    validateResult(sql("select percentile(1,array('0.5')) from Carbon_automation_test5"),"TC_479.csv");

  })

  //TC_480
  test("select percentile(1,array('1')) from Carbon_automation_test5") ({
    validateResult(sql("select percentile(1,array('1')) from Carbon_automation_test5"),"TC_480.csv");

  })

  //TC_485
  test("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test5") ({
    validateResult(sql("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test5"),"TC_485.csv");

  })

  //TC_486
  test("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test522") ({
    validateResult(sql("select percentile_approx(1,array(0.5),5000) from Carbon_automation_test5"),"TC_486.csv");

  })

  //TC_487
  test("select histogram_numeric(1, 5000)from Carbon_automation_test5") ({
    validateResult(sql("select histogram_numeric(1, 5000)from Carbon_automation_test5"),"TC_487.csv");

  })


  //TC_489
  test("select histogram_numeric(1, 500)from Carbon_automation_test52") ({
    validateResult(sql("select histogram_numeric(1, 500)from Carbon_automation_test5"),"TC_489.csv");

  })

  //TC_490
  test("select histogram_numeric(1, 500)from Carbon_automation_test53") ({
    validateResult(sql("select histogram_numeric(1, 500)from Carbon_automation_test5"),"TC_490.csv");

  })

  test("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test5")
  {
    checkAnswer(
      sql("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test5"),
      Seq(Row(1.0, 100006.6, 100016.4, 1000000.0)))

    //     validateResult(sql("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test5"),"TC_112.csv");
  }

}