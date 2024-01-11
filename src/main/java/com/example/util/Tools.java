package com.example.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.Date;
import java.util.Objects;

/**
 * 工具类
 *
 * @author : cchu
 * Date: 2023/3/23 14:51
 */
@Log4j2
public class Tools {

    /**
     * 快速排序
     */
    public class QuickSort {
        public static void quickSort(Integer[] arr, int left, int right) {
            if (left < right) {
                int pivotIndex = partition(arr, left, right);
                quickSort(arr, left, pivotIndex - 1);
                quickSort(arr, pivotIndex + 1, right);
            }
        }

        private static int partition(Integer[] arr, int left, int right) {
            int pivot = arr[right];
            int i = left - 1;
            for (int j = left; j < right; j++) {
                if (arr[j] < pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, right);
            return i + 1;
        }

        private static void swap(Integer[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * 查询是否周末或节假日
     * 如果true 不执行
     */
    public static Boolean runWeekend(Date date) {
        //api地址http://timor.tech/api/holiday/info/yyyy-MM-dd
        String url = "https://timor.tech/api/holiday/info/";
        String format = DateUtil.format(date, "yyyy-MM-dd");
        String result = HttpRequest.get(url + format).execute().body();
        JSONObject returnJson = JSONUtil.parseObj(result);
        log.info("当前时间详情：" + returnJson);
//        服务状态
        if ("0".equals(returnJson.get("code").toString())) {
            String type = returnJson.get("type").toString();
            System.out.println("日期详情" + type);
            // type  0 正常工作日 1 周末 2 节假日
            JSONObject entries = JSONUtil.parseObj(type);
            // 为正常工作日 返回false
            return !entries.get("type").equals(0);
        }
        return false;
    }

    /**
     * 获取文件的修改时间 如果不是当天的文件 则删除重新下载
     *
     * @param fileUrl
     * @return
     */
    static Boolean fileDate(String fileUrl) {
        File file = new File(fileUrl);
        if (file.length() == 0) {
            FileUtil.del(file);
        }
        String fileDate = DateUtil.format(new Date(file.lastModified()), "yyyy-MM-dd");
        String newDate = DateUtil.format(new Date(), "yyyy-MM-dd");
        if (!Objects.equals(fileDate, newDate)) {
            FileUtil.del(file);
            return false;
        }
        return true;
    }
}
