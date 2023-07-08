package com.wuyanzu.diancan.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Food;
import com.wuyanzu.diancan.mapper.FoodMapper;
import com.wuyanzu.diancan.service.FoodService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.wuyanzu.diancan.utils.HttpUtils;
import com.wuyanzu.diancan.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodMapper foodMapper;

//    private String path = "C:/Users/BricePC/Desktop/diancan/src/main/resources/static/image";

    private String ip="10.132.5.219";

    @Value("${server.port}")
    private String port;


    @ApiOperation("新增菜品")
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Food food, BindingResult bindingResult) throws IOException {
        log.info("food={}",food.toString());
        if(bindingResult.hasErrors()){
                return Result.error(201,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        foodService.save(food);
        return Result.success(200,"菜品新增成功",food);
    }



//    @PostMapping("/upload")
//    public Result uploadImage(@RequestParam MultipartFile file) throws IOException {
//        if(!file.isEmpty()){
//            String filename = file.getOriginalFilename();
//            File filepath = new File(path, filename);
//            if (!filepath.getParentFile().exists()) {
//                filepath.getParentFile().mkdirs();
//            }
//            file.transferTo(new File(path + File.separator + filename));
//            FileUtil.writeBytes(file.getBytes(),filepath);
//            String url = "http://" + "10.132.5.219" + ":" + "8080" + filepath.getAbsolutePath();
//            return Result.success(200,"菜品新增成功",filepath.getAbsolutePath());
//        }else {
//            return Result.error(201,"上传图片失败");
//        }
//    }

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/" + flag + "_" + originalFilename;  // 获取上传的路径
        File rootFile = new File(rootFilePath);
        if (!rootFile.getParentFile().exists()) {
            rootFile.getParentFile().mkdirs();
        }
        FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        return Result.success(200,"http://" + ip + ":" + port + "/food/" + flag + "_" + originalFilename,null);  // 返回结果 url
    }

//    @ApiOperation("获取图片")
//    @GetMapping("/{flag}")
//    public ResponseEntity<byte[]> getImg(@PathVariable String flag) {
//
//        //通过自己写的http工具类获取到图片输入流
//        InputStream inputStream = HttpUtils.getInputStream("http://" + ip + ":" + port + "/food/" + flag + ".png");
//        //将输入流转为byte[]
//        byte[] bytesByStream = getBytesByStream(inputStream);
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//
//        return new ResponseEntity<>(bytesByStream,headers, HttpStatus.OK);
//    }
//
//    public byte[]  getBytesByStream(InputStream inputStream){
//        byte[] bytes = new byte[2048];
//        int b;
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try {
//            while((b = inputStream.read(bytes)) != -1){
//
//                byteArrayOutputStream.write(bytes,0,b);
//            }
//            return byteArrayOutputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }




//    @ApiOperation("下载文件")
//    @GetMapping("/{flag}")
//    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
//        OutputStream os;  // 新建一个输出流对象
//        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/";  // 定于文件上传的根路径
//        List<String> fileNames = FileUtil.listFileNames(basePath);  // 获取所有的文件名称
//        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");  // 找到跟参数一致的文件
//        try {
//            if (StrUtil.isNotEmpty(fileName)) {
//                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//                response.setContentType("application/octet-stream");
//                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
//                os = response.getOutputStream();   // 通过输出流返回文件
//                os.write(bytes);
//                os.flush();
//                os.close();
//            }
//        } catch (Exception e) {
//            System.out.println("文件下载失败");
//        }
//    }



    @ApiOperation("得到提供所有菜品种类")
    @GetMapping("/getftype")
    public Result getFtype(){
        LambdaQueryWrapper<Food> queryWrapper = new LambdaQueryWrapper<>();
        //Page<Food> foodPage = new Page<>();
        queryWrapper.eq(Food::isFstatus,1);
        queryWrapper.groupBy(Food::getFtype);
        queryWrapper.select(Food::getFtype);
        queryWrapper.orderByDesc(Food::getFtype);
        List typeList = foodMapper.selectList(queryWrapper);
        //foodService.page(foodPage,queryWrapper);
        return Result.success(200,"",typeList);
    }

    @ApiOperation("得到所有菜品种类")
    @GetMapping("/getallftype")
    public Result getAllFtype(){
        LambdaQueryWrapper<Food> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.groupBy(Food::getFtype);
        queryWrapper.select(Food::getFtype);
        queryWrapper.orderByDesc(Food::getFtype);
        List typeList = foodMapper.selectList(queryWrapper);
        //foodService.page(foodPage,queryWrapper);
        return Result.success(200,"",typeList);
    }

    @ApiOperation("根据菜品种类排序后得到所有菜品信息")
    @GetMapping("/pageforftype")
    public Result pageForFtype(int page,int pageSize){
        Page<Food> foodPage = new Page<>(page,pageSize);
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("fstatus",1);
        queryWrapper.orderByDesc("ftype");
        IPage<Food> iPage = foodService.page(foodPage,queryWrapper);
        return Result.success(200,"该种类菜品",iPage);
    }

    @ApiOperation("根据菜品种类获得该种类菜品信息")
    @GetMapping("/pagebyftype")
    public Result pageByFtype(@RequestParam String ftype){
        log.info(ftype);
        Page<Food> foodPage = new Page<>();
        LambdaQueryWrapper<Food> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Food::isFstatus,1);
        queryWrapper.like(ftype!=null,Food::getFtype,ftype);
        queryWrapper.orderByDesc(Food::getFid);
        IPage<Food> iPage = foodService.page(foodPage,queryWrapper);
        if(queryWrapper.isEmptyOfNormal()){
            return Result.success(201,"没有该种类菜品",foodPage);
        }else{
            return Result.success(200,"该种类菜品",iPage);
        }
    }

    @ApiOperation("根据fid查找")
    @GetMapping("/get")
    public Result getByFid(@RequestParam Integer fid){
        Food food = foodService.getById(fid);
        return Result.success(200,"查找成功 ",food);
    }

    @ApiOperation("更新菜品信息")
    @PostMapping("/update")
    public Result update(@RequestBody @Valid Food food,BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return Result.error(201,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        log.info("food={}",food.toString());
        foodService.updateById(food);
        return Result.success(200,"更新成功",food);
    }

    @ApiOperation("只修改菜品状态")
    @PostMapping("/fstatus")
    public Result updateFstatus(@RequestParam("fstatus") boolean fstatus,@RequestParam("fid") Integer fid){
        log.info(String.valueOf(fstatus),fid);
        LambdaQueryWrapper<Food> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Food::getFid,fid);
        Food food = foodService.getOne(queryWrapper);
        food.setFstatus(fstatus);
        foodService.updateById(food);
        return Result.success(200,"状态修改成功",food.getFname());
    }

//    @ApiOperation("只修改菜品状态")
//    @PostMapping("/fstatus")
//    public Result updateFstatus(@RequestBody Food food){
//        log.info(String.valueOf(food));
//        LambdaQueryWrapper<Food> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Food::getFid,food.getFid());
//        Food food1 = foodService.getOne(queryWrapper);
//        foodService.updateById(food1);
//        return Result.success(200,"状态修改成功",food.getFname());
//    }

    @ApiOperation("删除菜品")
    @DeleteMapping("/delete")
    public Result deleteFood(Integer fid){
        String name = foodService.getById(fid).getFname();
        foodService.removeById(fid);
        return Result.success(200,"已删除",name);
    }


}
