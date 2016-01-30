package com.leo;


import java.util.ArrayList;

public class RamdomTest {

    public static void main(String[] args) {

        ExcelOperate data=new ExcelOperate();
        ArrayList<String> list=data.getDataList();

//        System.out.println(Math.random()*100);
//        int ramdom=(int) (Math.random()*10);

        int count =0;
        boolean isStop=false;
        ArrayList<Integer> data_Seq=new ArrayList<Integer>();
        for(int i=0;i<100;i++){
            int temp=(int) (Math.random()*100);
            if(temp<74&&!data_Seq.contains(temp)&&!isStop){
//                System.out.println(temp+" | "+count);
                data_Seq.add(temp);
                if(data_Seq.size()==30){
                    isStop=true;
                }
                ++count;
            }
        }
        System.out.println("data_Seq Size: "+data_Seq.size());
//        for(int i=0;i<data_Seq.size();i++){
//            System.out.println(data_Seq.get(i));
//        }

        //显示数组数据
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//        }
        int question_num=1;
        for(int i=1;i<list.size();i++){
//            System.out.println(list.get(i).substring(0, 2));
            String [] a =list.get(i).split("~");
//            System.out.println("a array length: "+a.length);
//            System.out.println("0 index: " +a[0]);
//            System.out.println(a[1]);
//            System.out.println(list.get(i).split("$")[0]);

//            if(data_Seq.contains(Integer.parseInt(list.get(i).substring(0, 2)))){
//                System.out.println(list.get(i).split("$")[1]);
//            }


            if(data_Seq.contains(Integer.parseInt(a[0]))){
                System.out.println(question_num+", "+a[1].trim());
                question_num++;
            }


        }

    }

}



