package cn.xzt.interview.controller;

import cn.xzt.interview.common.constant.ResultStatus;
import cn.xzt.interview.common.utils.PageUtil;
import cn.xzt.interview.common.utils.R;
import cn.xzt.interview.common.utils.StringUtil;
import cn.xzt.interview.domain.ProhibitWord;
import cn.xzt.interview.service.ProhibitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @Description:
 * @Auther: 周明军
 * @Date: 2018/10/23 11:09
 */
@RestController
@RequestMapping("/prohibit")
@Slf4j
public class ProhibitController {
    @Autowired
    private ProhibitService prohibitService;

    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
    public R list(HttpServletRequest request) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String keyWord = request.getParameter("keyWord");
        if (StringUtil.isBlank(pageNum) || StringUtil.isBlank(pageSize)) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        PageUtil<ProhibitWord> list = prohibitService.list(Integer.parseInt(pageNum), Integer.parseInt(pageSize), keyWord);
        return R.ok(list);
    }

    /**
     * 添加关键字
     *
     * @param request
     * @return
     */
    @PostMapping("/create")
    public R create(HttpServletRequest request) {
        String keyWord = request.getParameter("keyWord");
        if (StringUtil.isBlank(keyWord)) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        ProhibitWord prohibitWord = new ProhibitWord();
        prohibitWord.setWord(keyWord);
        prohibitService.create(prohibitWord);
        return R.ok(prohibitWord);
    }

    /**
     * 批量删除关键字
     *
     * @param request
     * @return
     */
    @PostMapping("/remove")
    public R remove(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        log.info("传入的ids为----------> {}", ids);
        if (StringUtil.isBlank(ids)) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        String[] split = ids.split(",");
        List<String> list = Arrays.asList(split);
        prohibitService.remove(list);
        return R.ok();
    }

    /**
     * 批量导入
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/batchImport") //, produces = "text/html;charset=UTF-8"
    public R batchImport(@RequestParam(value = "file", required = false) MultipartFile file) {
        if (null == file || file.isEmpty()) {
            return R.error(ResultStatus.PARAM_EMPTY.getCode(), ResultStatus.PARAM_EMPTY.getMessage());
        }
        String url = "C://temp.txt";
        try {
            file.transferTo(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File f = new File(url);
        ArrayList<String> list = new ArrayList<String>();
        InputStreamReader read = null;
        try {
            if (f.isFile() && f.exists()) {
                read = new InputStreamReader(new FileInputStream(f), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
            } else {
                log.info("找不到 {}", url);
            }
        } catch (Exception e) {
            log.info("读取 {} 出错", url);
        } finally {
            if (null != read) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Set<String> set = new HashSet<>(list);
        List<String> inList = new ArrayList<>(set);
        prohibitService.batchImport(inList);
        return R.ok();
    }

    @GetMapping("/listAll")
    public R listAll(){
        Set<String> allWord = prohibitService.getAllWord();
        return R.ok(allWord);
    }

}