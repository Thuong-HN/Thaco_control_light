#!/usr/bin/python3
import threading
import paho.mqtt.client as mqtt
import RPi.GPIO as GPIO
import time
import datetime
#import sys

GPIO.setmode(GPIO.BCM) # chon kieu danh so chan GPIO la BCM
path1lr , path2lr ,path3lr ,path4lr=   "/home/pi/thuong/THACO-LIGHT/data1lr.text","/home/pi/thuong/THACO-LIGHT/data2lr.text", "/home/pi/thuong/THACO-LIGHT/data3lr.text","/home/pi/thuong/THACO-LIGHT/data4lr.text"
path5lr , path6lr ,path7lr ,path8lr=   "/home/pi/thuong/THACO-LIGHT/data5lr.text","/home/pi/thuong/THACO-LIGHT/data6lr.text", "/home/pi/thuong/THACO-LIGHT/data7lr.text","/home/pi/thuong/THACO-LIGHT/data8lr.text"
path9lr,path10lr ,pathalllr   =  "/home/pi/thuong/THACO-LIGHT/data9lr.text", "/home/pi/thuong/THACO-LIGHT/data10lr.text" , "/home/pi/thuong/THACO-LIGHT/dataalllr.text"
path11lr,path12lr ,path13lr,path14lr,path15lr = "/home/pi/thuong/THACO-LIGHT/data11lr.text","/home/pi/thuong/THACO-LIGHT/data12lr.text", "/home/pi/thuong/THACO-LIGHT/data13lr.text", "/home/pi/thuong/THACO-LIGHT/data14lr.text","/home/pi/thuong/THACO-LIGHT/data15lr.text"
path16lr,path17lr,path18lr,path19lr,path20lr,path21lr = "/home/pi/thuong/THACO-LIGHT/data16lr.text", "/home/pi/thuong/THACO-LIGHT/data17lr.text", "/home/pi/thuong/THACO-LIGHT/data18lr.text","/home/pi/thuong/THACO-LIGHT/data19lr.text", "/home/pi/thuong/THACO-LIGHT/data20lr.text","/home/pi/thuong/THACO-LIGHT/data21lr.text"
path22lr,path23lr,path24lr,path25lr,path26lr,path27lr = "/home/pi/thuong/THACO-LIGHT/data22lr.text", "/home/pi/thuong/THACO-LIGHT/data23lr.text", "/home/pi/thuong/THACO-LIGHT/data24lr.text","/home/pi/thuong/THACO-LIGHT/data25lr.text", "/home/pi/thuong/THACO-LIGHT/data26lr.text","/home/pi/thuong/THACO-LIGHT/data27lr.text"
path28lr,path29lr,path30lr,path31lr,path32lr,path33lr = "/home/pi/thuong/THACO-LIGHT/data28lr.text", "/home/pi/thuong/THACO-LIGHT/data29lr.text", "/home/pi/thuong/THACO-LIGHT/data30lr.text","/home/pi/thuong/THACO-LIGHT/data31lr.text", "/home/pi/thuong/THACO-LIGHT/data32lr.text","/home/pi/thuong/THACO-LIGHT/data33lr.text"
path34lr,path35lr,path36lr,path37lr,path38lr,path39lr = "/home/pi/thuong/THACO-LIGHT/data34lr.text", "/home/pi/thuong/THACO-LIGHT/data35lr.text", "/home/pi/thuong/THACO-LIGHT/data36lr.text","/home/pi/thuong/THACO-LIGHT/data37lr.text", "/home/pi/thuong/THACO-LIGHT/data38lr.text","/home/pi/thuong/THACO-LIGHT/data39lr.text"
path40lr,path41lr,path42lr,path43lr,path44lr,path45lr = "/home/pi/thuong/THACO-LIGHT/data40lr.text", "/home/pi/thuong/THACO-LIGHT/data41lr.text", "/home/pi/thuong/THACO-LIGHT/data42lr.text","/home/pi/thuong/THACO-LIGHT/data43lr.text", "/home/pi/thuong/THACO-LIGHT/data44lr.text","/home/pi/thuong/THACO-LIGHT/data45lr.text"
path46lr,path47lr,path48lr,path49lr,path50lr,path51lr = "/home/pi/thuong/THACO-LIGHT/data46lr.text", "/home/pi/thuong/THACO-LIGHT/data47lr.text", "/home/pi/thuong/THACO-LIGHT/data48lr.text","/home/pi/thuong/THACO-LIGHT/data49lr.text", "/home/pi/thuong/THACO-LIGHT/data50lr.text","/home/pi/thuong/THACO-LIGHT/data51lr.text"
path52lr,path53lr,path54lr,path55lr,path56lr,path57lr = "/home/pi/thuong/THACO-LIGHT/data52lr.text", "/home/pi/thuong/THACO-LIGHT/data53lr.text", "/home/pi/thuong/THACO-LIGHT/data54lr.text","/home/pi/thuong/THACO-LIGHT/data55lr.text", "/home/pi/thuong/THACO-LIGHT/data56lr.text","/home/pi/thuong/THACO-LIGHT/data57lr.text"
path58lr,path59lr,path60lr,path61lr = "/home/pi/thuong/THACO-LIGHT/data58lr.text", "/home/pi/thuong/THACO-LIGHT/data59lr.text", "/home/pi/thuong/THACO-LIGHT/data60lr.text","/home/pi/thuong/THACO-LIGHT/data61lr.text"
path62lr,path63lr = "/home/pi/thuong/THACO-LIGHT/data62lr.text", "/home/pi/thuong/THACO-LIGHT/data63lr.text"
pathgiobatalllr,pathgphutbatalllr,pathgiotatalllr,pathphuttatalllr =  "/home/pi/thuong/THACO-LIGHT/datagiobatalllr.text", "/home/pi/thuong/THACO-LIGHT/dataphutbatalllr.text", "/home/pi/thuong/THACO-LIGHT/datagiotatalllr.text", "/home/pi/thuong/THACO-LIGHT/dataphuttatalllr.text"



# KHAI BAO BIEN
tuan=flag_tt_bat_tat1=flag_tt_bat_tat2=flag_tt_bat_tat3=flag_tt_bat_tat4=flag_tt_bat_tat5=flag_tt_bat_tat6=flag_tt_bat_tat7=flag_tt_bat_tat8=flag_tt_bat_tat9=flag_tt_bat_tat10=0
flag_hen_bat1=flag_hen_tat1 =flag_hen_bat2=flag_hen_tat2=flag_hen_bat3=flag_hen_tat3=flag_hen_bat4=flag_hen_tat4=flag_hen_bat5=flag_hen_tat5=0
flag_hen_bat6=flag_hen_tat6 =flag_hen_bat7=flag_hen_tat7=flag_hen_bat8=flag_hen_tat8=flag_hen_bat9=flag_hen_tat9=flag_hen_bat10=flag_hen_tat10=0
autoNM=autoLOA=tmp_autoNM=tmp_autoLOA_1=tmp_autoLOA_2=tmp_autoLOA_3=tmp_autoLOA_4=tmp_autoLOA_5=tmp_autoLOA_6=tmp_autoLOA_7=tmp_autoLOA_8=0
thuoftuan=thu='' 

hengio1=hengio2=hengio3=hengio4=hengio5=hengio6=hengio7=hengioall=hengio9=hengio10=''
giobat1=giotat1=giobat2=giotat2=giobat3=giotat3=giobat4=giotat4=giobat5=giotat5=giobat6=giotat6=giobat7=giotat7=giobat8=giotat8=giobat9=giotat9=giobat10=giotat10=''
tmp_giobat_1 =tmp_phutbat_1=tmp_giotat_1=tmp_phuttat_1=''
tmp_giobat_2 =tmp_phutbat_2=tmp_giotat_2=tmp_phuttat_2=''
tmp_giobat_3 =tmp_phutbat_3=tmp_giotat_3=tmp_phuttat_3=''
tmp_giobat_4 =tmp_phutbat_4=tmp_giotat_4=tmp_phuttat_4=''
tmp_giobat_5 =tmp_phutbat_5=tmp_giotat_5=tmp_phuttat_5=''
tmp_giobat_6 =tmp_phutbat_6=tmp_giotat_6=tmp_phuttat_6=''
tmp_giobat_7 =tmp_phutbat_7=tmp_giotat_7=tmp_phuttat_7=''
tmp_giobat_8 =tmp_phutbat_8=tmp_giotat_8=tmp_phuttat_8=''
tmp_giobat_9 =tmp_phutbat_9=tmp_giotat_9=tmp_phuttat_9=''
tmp_giobat_10 =tmp_phutbat_10=tmp_giotat_10=tmp_phuttat_10=''
autoLOA = tmp_autoLOA_1 = tmp_autoLOA_2 = tmp_autoLOA_3 = tmp_autoLOA_4 = tmp_autoLOA_5 = tmp_autoLOA_6 = tmp_autoLOA_7 = tmp_autoLOA_8 =0
tmp_nut_1=tmp_nut_2=tmp_nut_3=tmp_nut_4=tmp_nut_5=tmp_nut_6=tmp_nut_7=tmp_nut_8=tmp_nut_all = ''
tmp_nut_9=tmp_nut_10=tmp_nut_11=tmp_nut_12 = ''  


# BIEN GIU TRANG THAI NUT

GPIO.setwarnings(False)                         # Bo warning khi debug

# KHAI BAO CHAN XUAT TIN HIEU DIEU KHIEN DEN/ KICH RELAY
DEN_1,DEN_2,DEN_3,DEN_4,DEN_5,DEN_6,DEN_7,DEN_8 = 10,4,5,25,23,6,9,24
DEN_NHIP_MAU,LOA_THONG_BAO = 16,3 

#Khai bao chan su dung dieu khien

NUT_1,NUT_2 ,NUT_3,NUT_4,NUT_5,NUT_6,NUT_7,NUT_8 = 27,17,20,22,21,19,18,12
NUT_DEN_NHIP_MAU,NUT_LOA_THONG_BAO= 13,11
#Cau hinh chan su dung la chan doc hay xuat tin hieu (IN/OUT)
GPIO.setup(DEN_1, GPIO.OUT)
GPIO.setup(DEN_2, GPIO.OUT)
GPIO.setup(DEN_3, GPIO.OUT)
GPIO.setup(DEN_4, GPIO.OUT)
GPIO.setup(DEN_5, GPIO.OUT)
GPIO.setup(DEN_6, GPIO.OUT)
GPIO.setup(DEN_7, GPIO.OUT)
GPIO.setup(DEN_8, GPIO.OUT)

GPIO.setup(DEN_NHIP_MAU, GPIO.OUT)
GPIO.setup(LOA_THONG_BAO, GPIO.OUT)
GPIO.setup(2, GPIO.OUT) #CHAN RELAY TONG KICH RELAY KHI LUC KHOI DONG CO KET NOI INTERNET


#CHO CHAN KICH RELAY LEN MUC CAO --> RELAY --> 0 NHAM TAT TAT CA DEN KHI MOI KHOI DONG RASPBERRY
GPIO.output(DEN_1, 1)
GPIO.output(DEN_2, 1)
GPIO.output(DEN_3, 1)
GPIO.output(DEN_4, 1)
GPIO.output(DEN_5, 1)
GPIO.output(DEN_6, 1)
GPIO.output(DEN_7, 1)
GPIO.output(DEN_8, 1)


GPIO.output(DEN_NHIP_MAU,1)
GPIO.output(LOA_THONG_BAO, 1)
#print(GPIO.input(NUT_1))

#KHAI BAO TRANG THAI CHAN DIEU KHIEN LA KEO LEN (NHAN NUT THI -> 0)
GPIO.setup(NUT_1, GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(NUT_2, GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(NUT_3, GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(NUT_4, GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(NUT_5, GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(NUT_6, GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(NUT_7, GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(NUT_8, GPIO.IN,pull_up_down=GPIO.PUD_UP)

GPIO.setup(NUT_DEN_NHIP_MAU, GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(NUT_LOA_THONG_BAO, GPIO.IN,pull_up_down=GPIO.PUD_UP) # KEO LEN


TT_TAM_NUT_1 = TT_TAM_NUT_2 = TT_TAM_NUT_3 = TT_TAM_NUT_4= TT_TAM_NUT_5= TT_TAM_NUT_6= TT_TAM_NUT_7= TT_TAM_NUT_8  = TT_TAM_DEN_NHIP_MAU = TT_TAM_LOA_THONG_BAO = 0

print("setup")

           
GPIO.output(2, 1) # KHI CO KET NOI THI MOI SANG DEN BAO TRANG THAI
time.sleep(10)   # THOI GIAN DE RASPBERRY KET NOI INTERNET TRUOC KHI TIEN HANH CONNECT DEN SERVER ***************************************



try:
    def hengio():  # HEN GIO BAT/TAT
            global tmp_nut_1,tmp_nut_2,tmp_nut_3,tmp_nut_4,tmp_nut_5,tmp_nut_6,tmp_nut_7,tmp_nut_8,autoLOA,tmp_nut_all
            global tmp_nut_9,tmp_nut_10,tmp_nut_11,tmp_nut_12    
            global TT_TAM_NUT_1,TT_TAM_NUT_2,TT_TAM_NUT_3,TT_TAM_NUT_4,TT_TAM_NUT_5,TT_TAM_NUT_6,TT_TAM_NUT_7,TT_TAM_NUT_8
            global TT_TAM_DEN_NHIP_MAU,TT_TAM_LOA_THONG_BAO
            global flag_tt_bat_tat1,flag_tt_bat_tat2,flag_tt_bat_tat3,flag_tt_bat_tat4,flag_tt_bat_tat5,flag_tt_bat_tat6,flag_tt_bat_tatall,flag_tt_bat_tat8,flag_tt_bat_tat9,flag_tt_bat_tat10
            global flag_hen_bat1,flag_hen_tat1 ,flag_hen_bat2,flag_hen_tat2,flag_hen_bat3,flag_hen_tat3,flag_hen_bat4,flag_hen_tat4,flag_hen_bat5,flag_hen_tat5
            global flag_hen_bat6,flag_hen_tat6 ,flag_hen_batall,flag_hen_tatall,flag_hen_bat8,flag_hen_tat8,flag_hen_bat9,flag_hen_tat9,flag_hen_bat10,flag_hen_tat10
            global thu,thuoftuan,giobat,phutbat,giotat,phuttat 
            global hengio1,hengio2,hengio3,hengio4,hengio5,hengio6,hengioall,hengio8,hengio9,hengio10               
                             
            global tmp_giobat_1 ,tmp_phutbat_1,tmp_giotat_1,tmp_phuttat_1
            global tmp_giobat_2 ,tmp_phutbat_2,tmp_giotat_2,tmp_phuttat_2
            global tmp_giobat_3 ,tmp_phutbat_3,tmp_giotat_3,tmp_phuttat_3
            global tmp_giobat_4 ,tmp_phutbat_4,tmp_giotat_4,tmp_phuttat_4
            global tmp_giobat_5 ,tmp_phutbat_5,tmp_giotat_5,tmp_phuttat_5
            global tmp_giobat_6 ,tmp_phutbat_6,tmp_giotat_6,tmp_phuttat_6
            global tmp_giobat_7 ,tmp_phutbat_7,tmp_giotat_7,tmp_phuttat_7
            global tmp_giobat_8 ,tmp_phutbat_8,tmp_giotat_8,tmp_phuttat_8
            global tmp_giobat_9 ,tmp_phutbat_9,tmp_giotat_9,tmp_phuttat_9
            global tmp_giobat_10 ,tmp_phutbat_10,tmp_giotat_10,tmp_phuttat_10
            global tmp_autoLOA_1,tmp_autoLOA_2,tmp_autoLOA_3,tmp_autoLOA_4,tmp_autoLOA_5,tmp_autoLOA_6,tmp_autoLOA_7,tmp_autoLOA_8
        
            #print ("hengio********************")
            realtime = datetime.datetime.now()
            tuan = realtime.isoweekday()
            #print(tmp_ngaybat + " " +tmp_thangbat + " " +tmp_nambat+ " "+ tmp_giobat+ " " +tmp_phutbat+ " "+tmp_ngaytat+ " "+tmp_thangtat+ " "+tmp_namtat+" "+tmp_giotat+" "+tmp_phuttat)
            #print(str(time.isoweekday())+ " "+ str(time.hour)+ " " +str(time.minute))
            if(tuan == 1):
                thuoftuan = "THU2"
            if(tuan == 2):
                thuoftuan = "THU3"
            if(tuan == 3):
                thuoftuan = "THU4"
            if(tuan == 4):
                thuoftuan = "THU5"
            if(tuan == 5):
                thuoftuan = "THU6"
            if(tuan == 6):
                thuoftuan = "THU7"
            if(tuan == 7):
                thuoftuan = "CHUNHAT"
                
            #print(realtime)
            #print(tuan)
            #print(tmp_giobat_1)
            #print(autoLOA)
            
            
            with open(path11lr, 'r') as out:
                        thu= out.readline()
            
                           
            with open(path53lr, 'r') as out:
                        hengio2= out.readline()
                          
            with open(path54lr, 'r') as out:
                        hengio3= out.readline()
                         
            
                         
           
                        
            with open(path59lr, 'r') as out:
                        hengio8= out.readline()
                        
            with open(path60lr, 'r') as out:
                        hengio9= out.readline()
                         
            with open(path61lr, 'r') as out:
                        hengio10= out.readline()
            
            time.sleep(0.1)
                #auto hen gio loa thong bao
                #thu 3-4-5-6
            if(autoLOA == 1):
                    if( (tuan == 2) or (tuan == 3) or (tuan == 4) or (tuan == 5) or (tuan == 6) ) :
                        
                            
                        if( (realtime.hour == 7) and (realtime.minute == 30) and (tmp_autoLOA_2 == 0) and (tmp_autoLOA_1 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)  
                            #print("LOA")        
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_2 = 1
                        if( (realtime.hour == 10) and (realtime.minute == 25) and (tmp_autoLOA_3 == 0) and (tmp_autoLOA_2 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_3 = 1
                        if( (realtime.hour == 10) and (realtime.minute == 30) and (tmp_autoLOA_4 == 0) and (tmp_autoLOA_3 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_4 = 1
                        if( (realtime.hour == 11) and (realtime.minute == 25) and (tmp_autoLOA_5 == 0) and (tmp_autoLOA_4 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_5 = 1
                        if( (realtime.hour == 11) and (realtime.minute == 30) and (tmp_autoLOA_6 == 0) and (tmp_autoLOA_5 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_6 = 1
                        if( (realtime.hour == 16) and (realtime.minute == 25) and (tmp_autoLOA_7 == 0) and (tmp_autoLOA_6 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_7 = 1
                        if( (realtime.hour == 16) and (realtime.minute == 30) and (tmp_autoLOA_8 == 0) and (tmp_autoLOA_7 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_8 = 1
                            tmp_autoLOA_1 = 0
                            tmp_autoLOA_2 = 0
                            tmp_autoLOA_3 = 0
                            tmp_autoLOA_4 = 0
                            tmp_autoLOA_5 = 0
                            tmp_autoLOA_6 = 0
                            tmp_autoLOA_7 = 0
                    #thu 2
                    if(tuan == 1) :
                        if( (realtime.hour == 10) and (realtime.minute == 25) and (tmp_autoLOA_1 == 0)  ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_1 = 1
                            tmp_autoLOA_6 = 0
                        if( (realtime.hour == 10) and (realtime.minute == 30) and (tmp_autoLOA_2 == 0) and (tmp_autoLOA_1 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_2 = 1
                        if( (realtime.hour == 11) and (realtime.minute == 25) and (tmp_autoLOA_3 == 0) and (tmp_autoLOA_2 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_3 = 1
                        if( (realtime.hour == 11) and (realtime.minute == 30) and (tmp_autoLOA_4 == 0) and (tmp_autoLOA_3 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_4 = 1
                        if( (realtime.hour == 16) and (realtime.minute == 25) and (tmp_autoLOA_5 == 0) and (tmp_autoLOA_4 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_5 = 1
                        if( (realtime.hour == 16) and (realtime.minute == 30) and (tmp_autoLOA_6 == 0) and (tmp_autoLOA_5 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_6 = 1
                            
                            tmp_autoLOA_1 = 0
                            tmp_autoLOA_2 = 0
                            tmp_autoLOA_3 = 0
                            tmp_autoLOA_4 = 0
                            tmp_autoLOA_5 = 0
                            
                    #chu nhat
                    if(tuan == 7) :
                        if( (realtime.hour == 7) and (realtime.minute == 30) and (tmp_autoLOA_1 == 0)  ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_1 = 1
                            tmp_autoLOA_4 = 0
                        if( (realtime.hour == 10) and (realtime.minute == 30) and (tmp_autoLOA_2 == 0) and (tmp_autoLOA_1 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_2 = 1
                        if( (realtime.hour == 11) and (realtime.minute == 30) and (tmp_autoLOA_3 == 0) and (tmp_autoLOA_2 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_3 = 1
                        if( (realtime.hour == 16)  and (tmp_autoLOA_4 == 0) and (tmp_autoLOA_3 == 1) ): 
                            GPIO.output(LOA_THONG_BAO, 0)          
                            time.sleep(1)
                            GPIO.output(LOA_THONG_BAO, 1)
                            tmp_autoLOA_4 = 1
                        
                            tmp_autoLOA_1 = 0
                            tmp_autoLOA_2 = 0
                            tmp_autoLOA_3 = 0
            
            if(flag_hen_bat1 == 0 and flag_tt_bat_tat1==0 and hengio1=="henbat1"):
                with open(path12lr, 'r') as out:
                        tmp_giobat_1= out.readline()
                with open(path13lr, 'r') as out:
                        tmp_phutbat_1= out.readline()
                #time.sleep(0.1)
                if((thuoftuan == thu) and (str(realtime.hour) == tmp_giobat_1) and (str(realtime.minute) == tmp_phutbat_1)): #and(str(time.hour) == tmp_giobat) and (str(time.minute) == tmp_phutbat)
                    #print("BAT HEN GIO 1")
                    GPIO.output(DEN_1, 0)
                    with open(path52lr, 'w') as out:              
                            out.write("henbat1")
                    with open(path1lr, 'w') as out:              
                            out.write('1')
                    TT_TAM_NUT_1 = 1
                    flag_tt_bat_tat1 = 1
                    flag_hen_bat1 = 1
                    flag_hen_tat1 = 0
                                 
            if(flag_hen_tat1 == 0 and flag_tt_bat_tat1==1):
                with open(path14lr, 'r') as out:
                        tmp_giotat_1= out.readline()
                with open(path15lr, 'r') as out:
                        tmp_phuttat_1= out.readline()
                #time.sleep(0.1)
                if((thuoftuan == thu)and (str(realtime.hour) == tmp_giotat_1) and (str(realtime.minute) >= tmp_phuttat_1)):
                    #print("TAT HEN GIO 1")
                    GPIO.output(DEN_1, 1)
                    with open(path52lr, 'w') as out:              
                            out.write("hentat1")
                            
                    with open(path1lr, 'w') as out:              
                            out.write('0')
                    TT_TAM_NUT_1 = 0        
                    flag_hen_tat1 = 1
                    flag_tt_bat_tat1=0
                    flag_hen_bat1 = 0
                    
            if(flag_hen_bat2 == 0 and flag_tt_bat_tat2==0 and hengio2=="henbat2"):
                with open(path16lr, 'r') as out:
                        tmp_giobat_2= out.readline()
                with open(path17lr, 'r') as out:
                        tmp_phutbat_2= out.readline()
                time.sleep(0.1)
                if((thuoftuan == thu)and(str(realtime.hour) == tmp_giobat_2) and (str(realtime.minute) == tmp_phutbat_2)): #and(str(time.hour) == tmp_giobat) and (str(time.minute) == tmp_phutbat)
                    #print("BAT HEN GIO 2")
                    GPIO.output(DEN_2, 0)
                    with open(path53lr, 'w') as out:              
                            out.write("henbat2")
                    with open(path2lr, 'w') as out:              
                            out.write('1')
                    TT_TAM_NUT_2 = 1
                    flag_tt_bat_tat2 = 1
                    flag_hen_bat2 = 1
                    flag_hen_tat2 = 0
                    
            if(flag_hen_tat2 == 0 and flag_tt_bat_tat2==1 ):
                with open(path18lr, 'r') as out:
                        tmp_giotat_2= out.readline()
                with open(path19lr, 'r') as out:
                        tmp_phuttat_2= out.readline()
                time.sleep(0.1)
                if((thuoftuan == thu)and (str(realtime.hour) == tmp_giotat_2) and (str(realtime.minute) >= tmp_phuttat_2)):
                    #print("TAT HEN GIO 2")
                    GPIO.output(DEN_2, 1)
                    with open(path53lr, 'w') as out:              
                            out.write("hentat2")           
                    with open(path2lr, 'w') as out:              
                            out.write('0')
                    flag_hen_bat2 = 0
                    TT_TAM_NUT_2 = 0    
                    flag_hen_tat2 = 1
                    flag_tt_bat_tat2=0
                    
            if(flag_hen_bat3 == 0 and flag_tt_bat_tat3==0 and hengio3=="henbat3"):
                with open(path20lr, 'r') as out:
                        tmp_giobat_3= out.readline()
                with open(path21lr, 'r') as out:
                        tmp_phutbat_3= out.readline()
                time.sleep(0.1)
                if((thuoftuan == thu)and(str(realtime.hour) == tmp_giobat_3) and (str(realtime.minute) == tmp_phutbat_3)): #and(str(time.hour) == tmp_giobat) and (str(time.minute) == tmp_phutbat)
                    #print("BAT HEN GIO 3")
                    GPIO.output(DEN_3, 0)
                    with open(path54lr, 'w') as out:              
                            out.write("henbat3")
                    with open(path3lr, 'w') as out:              
                            out.write('1')
                    TT_TAM_NUT_3 = 1
                    flag_tt_bat_tat3 = 1
                    flag_hen_bat3 = 1
                    flag_hen_tat3 = 0
                    
            if(flag_hen_tat3 == 0 and flag_tt_bat_tat3==1 ):
                with open(path22lr, 'r') as out:
                        tmp_giotat_3= out.readline()
                with open(path23lr, 'r') as out:
                        tmp_phuttat_3= out.readline()
                time.sleep(0.1) 
                if((thuoftuan == thu)and (str(realtime.hour) == tmp_giotat_3) and (str(realtime.minute) >= tmp_phuttat_3)):
                    #print("TAT HEN GIO 3")
                    GPIO.output(DEN_3, 1)
                    with open(path54lr, 'w') as out:              
                            out.write("hentat3")           
                    with open(path3lr, 'w') as out:              
                            out.write('0')
                    TT_TAM_NUT_3 = 0
                    flag_hen_tat3 = 1
                    flag_tt_bat_tat3=0
                    flag_hen_bat3 = 0
                    
            if(flag_hen_bat4== 0 and flag_tt_bat_tat4==0 and hengio4=="henbat4"):
                with open(path24lr, 'r') as out:
                        tmp_giobat_4= out.readline()
                with open(path25lr, 'r') as out:
                        tmp_phutbat_4= out.readline()
                time.sleep(0.1)
                if((thuoftuan == thu)and(str(realtime.hour) == tmp_giobat_4) and (str(realtime.minute) == tmp_phutbat_4)): #and(str(time.hour) == tmp_giobat) and (str(time.minute) == tmp_phutbat)
                    #print("BAT HEN GIO 4")
                    GPIO.output(DEN_4, 0)
                    with open(path55lr, 'w') as out:              
                            out.write("henbat4")
                    with open(path4lr, 'w') as out:              
                            out.write('1')
                    TT_TAM_NUT_4 = 1
                    flag_tt_bat_tat4 = 1
                    flag_hen_bat4 = 1
                    flag_hen_tat4 = 0
                    
            if(flag_hen_tat4 == 0 and flag_tt_bat_tat4==1 ):
                with open(path26lr, 'r') as out:
                        tmp_giotat_4= out.readline()
                with open(path27lr, 'r') as out:
                        tmp_phuttat_4= out.readline()
                time.sleep(0.1)
                if((thuoftuan == thu)and (str(realtime.hour) == tmp_giotat_4) and (str(realtime.minute) >= tmp_phuttat_4)):
                    #print("TAT HEN GIO 4")
                    GPIO.output(DEN_4, 1)
                    with open(path55lr, 'w') as out:              
                            out.write("hentat4")           
                    with open(path4lr, 'w') as out:              
                            out.write('0')
                    TT_TAM_NUT_4 = 0
                    flag_hen_tat4 = 1
                    flag_tt_bat_tat4=0
                    flag_hen_bat4 = 0
                    
                               
            
                    
            if(flag_hen_bat9 == 0 and flag_tt_bat_tat9==0 and hengio9=="henbat9"):
                with open(path44lr, 'r') as out:
                        tmp_giobat_9= out.readline()
                with open(path45lr, 'r') as out:
                        tmp_phutbat_9= out.readline()
                time.sleep(0.1)
                if((thuoftuan == thu)and(str(realtime.hour) == tmp_giobat_9) and (str(realtime.minute) == tmp_phutbat_9)): #and(str(time.hour) == tmp_giobat) and (str(time.minute) == tmp_phutbat)
                    #print("BAT HEN GIO 9")
                    GPIO.output(DEN_NHIP_MAU, 0)
                    with open(path60lr, 'w') as out:              
                            out.write("henbat9")
                    with open(path9lr, 'w') as out:              
                            out.write('1')
                    TT_TAM_DEN_NHIP_MAU = 1
                    flag_tt_bat_tat9 = 1
                    flag_hen_bat9 = 1
                    flag_hen_tat9 = 0
                   
            if(flag_hen_tat9 == 0 and flag_tt_bat_tat9==1):
                with open(path46lr, 'r') as out:
                        tmp_giotat_9= out.readline()
                with open(path47lr, 'r') as out:
                        tmp_phuttat_9= out.readline()
                time.sleep(0.1)
                if((thuoftuan == thu)and(str(realtime.hour) == tmp_giotat_9) and (str(realtime.minute) >= tmp_phuttat_9)): #and(str(time.hour) == tmp_giobat) and (str(time.minute) == tmp_phutbat)
                    #print("TAT HEN GIO 9")
                    GPIO.output(DEN_NHIP_MAU, 1)
                    with open(path60lr, 'w') as out:              
                            out.write("hentat9")
                    with open(path9lr, 'w') as out:              
                            out.write('0')
                    TT_TAM_DEN_NHIP_MAU = 0
                    flag_tt_bat_tat9 = 1
                    flag_hen_tat9 = 1
                    flag_hen_bat9 = 0
                    
            if(flag_hen_bat10 == 0 and flag_tt_bat_tat10==0 and hengio10=="henbat10"):
                with open(path48lr, 'r') as out:
                        tmp_giobat_10= out.readline()
                with open(path49lr, 'r') as out:
                        tmp_phutbat_10= out.readline()
                time.sleep(0.1)
                if((thuoftuan == thu)and(str(realtime.hour) == tmp_giobat_10) and (str(realtime.minute) == tmp_phutbat_10)): #and(str(time.hour) == tmp_giobat) and (str(time.minute) == tmp_phutbat)
                    #print("BAT HEN GIO 10")
                    GPIO.output(LOA_THONG_BAO, 0)
                    time.sleep(1)
                    GPIO.output(LOA_THONG_BAO, 1)
                    with open(path61lr, 'w') as out:              
                            out.write("henbat10")
                    with open(path10lr, 'w') as out:              
                            out.write('1')
                    TT_TAM_LOA_THONG_BAO = 1
                    flag_tt_bat_tat10 = 1
                    flag_hen_bat10 = 1
                    flag_hen_tat10 = 0
                    
            if(flag_hen_tat10 == 0 and flag_tt_bat_tat10==1 ):
                with open(path50lr, 'r') as out:
                        tmp_giotat_10= out.readline()
                with open(path51lr, 'r') as out:
                        tmp_phuttat_10= out.readline()
                time.sleep(0.1) 
                if((thuoftuan == thu)and(str(realtime.hour) == tmp_giotat_10) and (str(realtime.minute) >= tmp_phuttat_10)): #and(str(time.hour) == tmp_giobat) and (str(time.minute) == tmp_phutbat)
                    #print("TAT HEN GIO 10")
                    GPIO.output(LOA_THONG_BAO, 1)
                    with open(path61lr, 'w') as out:              
                            out.write("hentat10")
                    with open(path10lr, 'w') as out:              
                            out.write('0')
                    TT_TAM_LOA_THONG_BAO = 0
                    flag_tt_bat_tat10 = 1
                    flag_hen_tat10 = 1
                    flag_hen_bat10 = 0
                    
      

    # HAM XU LY KET NOI DEN BROKER (SERVER)                
    def on_connect(mqttc, obj, flags, rc):               
            mqttc.subscribe("dkesp32",0) # DANG KY TOPIC NHAN TIN TU ANDROID
    
            print ("CONNECTED**************************")
            #time.sleep(0.5)
            
    def on_disconnect(mqttc, obj, rc):
        #print("dis*****************")
        pass
    def on_publish(mqttc, obj, mid):
        
        pass

    def on_subscribe(mqttc, obj, mid, granted_qos):
        pass
                                         
                                         
    def on_log(mqttc, obj, level, string):
        pass

    # NHAN TIN HIEU TU SERVER THI TIEN HANH DIEU KHIEN HOAC LAM CAC TAC VU
    def on_message(mqttc, obj, msg):
            global flag_hen_bat1,flag_hen_tat1 ,flag_hen_bat2,flag_hen_tat2,flag_hen_bat3,flag_hen_tat3,flag_hen_bat4,flag_hen_tat4
            global flag_hen_bat6,flag_hen_tat6 ,flag_hen_batall,flag_hen_tatall,flag_hen_bat8,flag_hen_tat8,flag_hen_bat9,flag_hen_tat9,flag_hen_bat10,flag_hen_tat10
            global TT_TAM_NUT_1,TT_TAM_NUT_2,TT_TAM_NUT_3,TT_TAM_NUT_4,TT_TAM_NUT_5,TT_TAM_NUT_6,TT_TAM_NUT_7,TT_TAM_NUT_8
            global TT_TAM_LOA_THONG_BAO,TT_TAM_DEN_NHIP_MAU   
            global autoLOA
            global thu,giobat1,phutbat1,giotat1,phuttat1
            global giobat2,phutbat2,giotat2,phuttat2
            global giobat3,phutbat3,giotat3,phuttat3
            
            global giobat6,phutbat6,giotat6,phuttat6
            global giobatall,phutbatall,giotatall,phuttatall
            global giobat8,phutbat8,giotat8,phuttat8
            global giobat9,phutbat9,giotat9,phuttat9
            global giobat10,phutbat10,giotat10,phuttat10
            global tmp_nut_1,tmp_nut_2,tmp_nut_3,tmp_nut_4,tmp_nut_5,tmp_nut_6,tmp_nut_7,tmp_nut_8,tmp_nut_all
            global tmp_nut_9,tmp_nut_10 ,tmp_nut_11,tmp_nut_12 
            
            global hengioall,gio_bat_all,phut_bat_all,gio_tat_all,phut_tat_all
            
            tmp = msg.payload.decode("utf-8")     # python3 tra ve byte khong phai string
            try:
                get_data = str(tmp)
            except:
                get_data = 'null'
            #print("Nhan duoc: %s" % (get_data))
            
            # NHAN TRANG THAI NUT NHAN 
            if(get_data=="on1"): #bat DEN_1
                    GPIO.output(DEN_1, 0)          #RELAY HIGH - TURN ON BUTTON LIGHT
                    TT_TAM_NUT_1 = 1
                    with open(path1lr, 'w') as out:              
                        out.write('1')
                                        
                        
            if(get_data=="off1"): #tat DEN_1
                    GPIO.output(DEN_1, 1)
                    TT_TAM_NUT_1 = 0
                    with open(path1lr, 'w') as out:              
                        out.write('0')
                    #print("TAT 1")
                     
            if(get_data=="on2"): #bat DEN_2
                    GPIO.output(DEN_2, 0)
                    TT_TAM_NUT_2 = 1
                    with open(path2lr, 'w') as out:              
                        out.write('1')
                   # print("BAT NUT 2")    
                    
            if(get_data=="off2"): #tat DEN_2
                    GPIO.output(DEN_2, 1)
                    TT_TAM_NUT_2 = 0
                    with open(path2lr, 'w') as out:              
                        out.write('0')
                    #print("TAT NUT 2")    
                        
                        
            if(get_data=="on3"): #bat DEN_3
                    GPIO.output(DEN_3, 0)
                    TT_TAM_NUT_3 = 1
                    with open(path3lr, 'w') as out:              
                        out.write('1')
                    #print("BAT 3") 
                        
            if(get_data=="off3"): #tat DEN_3
                    GPIO.output(DEN_3, 1)
                    TT_TAM_NUT_3 = 0
                    with open(path3lr, 'w') as out:              
                        out.write('0')
                    #print("TAT 3")   
                     
            if(get_data=="on4"): #bat DEN_4
                    GPIO.output(DEN_4, 0)
                    TT_TAM_NUT_4 = 1
                    with open(path4lr, 'w') as out:              
                        out.write('1')
	
                        
            if(get_data=="off4"): #tat DEN_4
                    GPIO.output(DEN_4, 1)
                    TT_TAM_NUT_4 = 0
                    with open(path4lr, 'w') as out:              
                        out.write('0')
                        
            if(get_data=="on5"): #bat DEN_4
                    GPIO.output(DEN_5, 0)
                    TT_TAM_NUT_5 = 1
                    with open(path5lr, 'w') as out:              
                        out.write('1')
		
                        
            if(get_data=="off5"): #tat DEN_4
                    GPIO.output(DEN_5, 1)
                    TT_TAM_NUT_5 = 0
                    with open(path5lr, 'w') as out:              
                        out.write('0')
                        
            if(get_data=="on6"): #bat DEN_4
                    GPIO.output(DEN_6, 0)
                    TT_TAM_NUT_6 = 1
                    with open(path6lr, 'w') as out:              
                        out.write('1')
		
                        
            if(get_data=="off6"): #tat DEN_4
                    GPIO.output(DEN_6, 1)
                    TT_TAM_NUT_6 = 0
                    with open(path6lr, 'w') as out:              
                        out.write('0')
                        
            if(get_data=="on7"): #bat DEN_4
                    GPIO.output(DEN_7, 0)
                    TT_TAM_NUT_7 = 1
                    with open(path7lr, 'w') as out:              
                        out.write('1')
		
                        
            if(get_data=="off7"): #tat DEN_4
                    GPIO.output(DEN_7, 1)
                    TT_TAM_NUT_7 = 0
                    with open(path7lr, 'w') as out:              
                        out.write('0')
                        
            if(get_data=="on8"): #bat DEN_4
                    GPIO.output(DEN_8, 0)
                    TT_TAM_NUT_8 = 1
                    with open(path8lr, 'w') as out:              
                        out.write('1')
		
                        
            if(get_data=="off8"): #tat DEN_4
                    GPIO.output(DEN_8, 1)
                    TT_TAM_NUT_8 = 0
                    with open(path8lr, 'w') as out:              
                        out.write('0')
		            
            if(get_data=="onall"): #bat tat ca den nha xuong
                    GPIO.output(DEN_1, 0)
                    #time.sleep(1)
                    GPIO.output(DEN_2, 0)
                    GPIO.output(DEN_3, 0)
                    GPIO.output(DEN_4, 0)
                    
                    GPIO.output(DEN_5, 0)
                    GPIO.output(DEN_6, 0)
                    GPIO.output(DEN_7, 0)
                    GPIO.output(DEN_8, 0)
                    #TT_TAM_NUT_all = 1
                    with open(pathalllr, 'w') as out:              
                        out.write('1')
                    TT_TAM_NUT_8 = 1
                    TT_TAM_NUT_7 = 1
                    TT_TAM_NUT_6 = 1
                    TT_TAM_NUT_5 = 1
                    TT_TAM_NUT_4 = 1
                    TT_TAM_NUT_3 = 1
                    TT_TAM_NUT_2 = 1
                    TT_TAM_NUT_1 = 1
                    TT_TAM_DEN_NHIP_MAU = 1
                        
            if(get_data=="offall"): #tat tat ca den nha xuong
                    GPIO.output(DEN_1, 1)
                    GPIO.output(DEN_2, 1)
                    GPIO.output(DEN_3, 1)
                    GPIO.output(DEN_4, 1)
                    
                    GPIO.output(DEN_5, 1)
                    GPIO.output(DEN_6, 1)
                    GPIO.output(DEN_7, 1)
                    GPIO.output(DEN_8, 1)
                    #TT_TAM_NUT_all = 0
                    with open(pathalllr, 'w') as out:              
                        out.write('0')
                    
                    TT_TAM_NUT_8 = 0
                    TT_TAM_NUT_7 = 0
                    TT_TAM_NUT_6 = 0
                    TT_TAM_NUT_5 = 0
                    TT_TAM_NUT_4 = 0
                    TT_TAM_NUT_3 = 0
                    TT_TAM_NUT_2 = 0
                    TT_TAM_NUT_1 = 0
                    TT_TAM_DEN_NHIP_MAU = 0
                      
                     
            if(get_data=="on9"): #bat DEN NHIP MAU
                    GPIO.output(DEN_NHIP_MAU, 0)
                    TT_TAM_DEN_NHIP_MAU = 1
                    with open(path9lr, 'w') as out:              
                        out.write('1')
                         
            if(get_data=="off9"): #tat DEN NHIP MAU
                    GPIO.output(DEN_NHIP_MAU, 1)
                    TT_TAM_DEN_NHIP_MAU = 0
                    with open(path9lr, 'w') as out:              
                        out.write('0')
                   
            
                        
            if(get_data=="onauto10"): #bat LOA THONG BAO (chua dung)
                    autoLOA = 1
                    with open(path62lr, 'w') as out:              
                        out.write('1')
                                               
            if(get_data=="offauto10"): #tat LOA THONG BAO (chua dung)
                    autoLOA = 0
                    with open(path62lr, 'w') as out:              
                        out.write('0')
                    
            if(get_data == "getolddata32"): # NHAN TIN HIEU UPDATE TRANG THAI TU ANDROID
                
                #print ("OK------------------")
                with open(path1lr, 'r') as out:
                    tmp_nut_1= out.readline()
                    #print(tmp_nut_1)
                with open(path2lr, 'r') as out:
                    tmp_nut_2= out.readline()
                    #print(tmp_nut_2)
                with open(path3lr, 'r') as out:
                    tmp_nut_3= out.readline()
                    #print(tmp_nut_3)
                with open(path4lr, 'r') as out:
                    tmp_nut_4= out.readline()
                with open(path5lr, 'r') as out:
                    tmp_nut_5= out.readline()
                with open(path6lr, 'r') as out:
                    tmp_nut_6= out.readline()
                with open(path7lr, 'r') as out:
                    tmp_nut_7= out.readline()
                with open(path8lr, 'r') as out:
                    tmp_nut_8= out.readline()
                    #print(tmp_nut_4)
                with open(pathalllr, 'r') as out:
                    tmp_nut_all= out.readline()
                with open(path9lr, 'r') as out:
                    tmp_nut_9= out.readline()
                    #print(tmp_nut_9)
                with open(path62lr, 'r') as out:
                    tmp_nut_11= out.readline()
              
                mqttc.publish("dataesp32","dknutNL"+" "+tmp_nut_1+" "+tmp_nut_2+" "+tmp_nut_3+" "+tmp_nut_4+" "+tmp_nut_5+" "+tmp_nut_6+" "+tmp_nut_7+" "+tmp_nut_8+" "+tmp_nut_9+" "+tmp_nut_11+" "+tmp_nut_all)            
                
            
          
   
    mqttc = mqtt.Client("NL",clean_session = False)
    mqttc.on_connect = on_connect
    mqttc.on_disconnect = on_disconnect # dis se dc goi khi co ket noi lai tu rasp
    mqttc.on_message = on_message       
    mqttc.on_publish = on_publish
    mqttc.on_subscribe = on_subscribe

    #mqttc.username_pw_set("vtzebdqb","wbqkA68b6iej") KET NOI DEN BROKER DAT TREN CLOUD                             -> GLOBAL
    #mqttc.connect("m15.cloudmqtt.com", 10432,60) #dien IP cua Pi, vd: 192.168.1.77
    mqttc.connect("10.11.15.162", 1883,30) #dien IP cua Pi, vd: 192.168.1.77 KET NOI DEN BROKER DAT TAI RASPBERRY   -> LOCAL
    mqttc.loop_start()
    
    # XU LY NUT NHAN 
   
     
    while 1:
        if(GPIO.input(NUT_1)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_1)is 0):
                if   TT_TAM_NUT_1 == 0 :
                
                            GPIO.output(DEN_1, 0)
                            TT_TAM_NUT_1 = 1
                                
                                #time.sleep(0.1)
                            with open(path1lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_1) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_NUT_1 == 1 :
                            GPIO.output(DEN_1, 1)
                            TT_TAM_NUT_1 = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path1lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_1) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********")
        elif(GPIO.input(NUT_2)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_2)is 0):
                if   TT_TAM_NUT_2 == 0 :
                
                            GPIO.output(DEN_2, 0)
                            TT_TAM_NUT_2 = 1
                                
                                #time.sleep(0.1)
                            with open(path2lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_2) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_NUT_2 == 1 :
                            GPIO.output(DEN_2, 1)
                            TT_TAM_NUT_2 = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path2lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_2) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********") 
                            
        elif(GPIO.input(NUT_3)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_3)is 0):
                if   TT_TAM_NUT_3 == 0 :
                
                            GPIO.output(DEN_3, 0)
                            TT_TAM_NUT_3 = 1
                                
                                #time.sleep(0.1)
                            with open(path3lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_3) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_NUT_3 == 1 :
                            GPIO.output(DEN_3, 1)
                            TT_TAM_NUT_3 = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path3lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_3) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********") 
                            
        elif(GPIO.input(NUT_4)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_4)is 0):
                if   TT_TAM_NUT_4 == 0 :
                
                            GPIO.output(DEN_4, 0)
                            TT_TAM_NUT_4 = 1
                                
                                #time.sleep(0.1)
                            with open(path4lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_4) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_NUT_4 == 1 :
                            GPIO.output(DEN_4, 1)
                            TT_TAM_NUT_4 = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path4lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_4) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********") 
                            
        elif(GPIO.input(NUT_5)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_5)is 0):
                if   TT_TAM_NUT_5 == 0 :
                
                            GPIO.output(DEN_5, 0)
                            TT_TAM_NUT_5 = 1
                                
                                #time.sleep(0.1)
                            with open(path5lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_5) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_NUT_5 == 1 :
                            GPIO.output(DEN_5, 1)
                            TT_TAM_NUT_5 = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path5lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_5) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********") 
                            
        elif(GPIO.input(NUT_6)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_6)is 0):
                if   TT_TAM_NUT_6 == 0 :
                
                            GPIO.output(DEN_6, 0)
                            TT_TAM_NUT_6 = 1
                                
                                #time.sleep(0.1)
                            with open(path6lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_6) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_NUT_6 == 1 :
                            GPIO.output(DEN_6, 1)
                            TT_TAM_NUT_6 = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path6lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_6) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********") 
                            
        elif(GPIO.input(NUT_7)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_7)is 0):
                if   TT_TAM_NUT_7 == 0 :
                
                            GPIO.output(DEN_7, 0)
                            TT_TAM_NUT_7 = 1
                                
                                #time.sleep(0.1)
                            with open(path7lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_7) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_NUT_7 == 1 :
                            GPIO.output(DEN_7, 1)
                            TT_TAM_NUT_7 = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path7lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_7) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********") 
                            
        elif(GPIO.input(NUT_8)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_8)is 0):
                if   TT_TAM_NUT_8 == 0 :
                
                            GPIO.output(DEN_8, 0)
                            TT_TAM_NUT_8 = 1
                                
                                #time.sleep(0.1)
                            with open(path8lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_8) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_NUT_8 == 1 :
                            GPIO.output(DEN_8, 1)
                            TT_TAM_NUT_8 = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path8lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_8) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********") 
                            
        elif (GPIO.input(NUT_DEN_NHIP_MAU)is 0):
            
            time.sleep(0.3)
            if(GPIO.input(NUT_DEN_NHIP_MAU)is 0):
                if   TT_TAM_DEN_NHIP_MAU == 0 :
                
                            GPIO.output(DEN_NHIP_MAU, 0)
                            TT_TAM_DEN_NHIP_MAU = 1
                                
                                #time.sleep(0.1)
                            with open(path9lr, 'w') as out:              
                                    out.write('1')
                            time.sleep(0.03)
                            while GPIO.input(NUT_DEN_NHIP_MAU) is 0 :
                                time.sleep(0.01)
                            #print("BAT NUT 1***********") 
                                        
                elif   TT_TAM_DEN_NHIP_MAU == 1 :
                            GPIO.output(DEN_NHIP_MAU, 1)
                            TT_TAM_DEN_NHIP_MAU = 0
                               
                                
                                #time.sleep(0.1)
                            with open(path9lr, 'w') as out:              
                                    out.write('0')
                            time.sleep(0.03)
                            while GPIO.input(NUT_DEN_NHIP_MAU) is 0 :
                                time.sleep(0.01)
                            #print("TAT NUT 1***********") 
        time.sleep(0.03)
        
except KeyboardInterrupt:
    GPIO.cleanup()
    mqttc.loop_stop()
