// Music.aidl
package io.piazi.quiz;
//AIDL文件只支持
//基本类型(int/long/chat/boolean/double) & String & ArrayList & HashMap & Parcelable & AIDL文件
//自定义的Parcelable和AIDL文件必须显示import进来
import io.piazi.quiz.Music;

interface IMusicManager {
    List<Music> getMusicList();
    //in表示输入型参数 out表示输出型参数 inout表示输入输出型参数
    void addBook(in Music music);
}
