package com.example.greenday.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableArrayList;

import com.example.greenday.network.API;
import com.example.greenday.network.Network;
import com.example.greenday.network.Track;
import com.example.greenday.network.TrackSearchResult;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;

@Getter
public class RepositoryImpl implements Repository {

    private final API api = Network.getInstance().create(API.class);
    private final ObservableArrayList<Track> trackList, favorite;

    public RepositoryImpl(){
        trackList = new ObservableArrayList<>();
        favorite = new ObservableArrayList<>();
    }

    @Override
    public void loadTrackList(int offset, int limit) {
        // 트랙 리스트 로드
        // 룸에 있나 확인
        // 트랙 별 체크
        api.search("greenday", "song", offset, limit)
                .enqueue(new Callback<TrackSearchResult>() {
                    @Override
                    public void onResponse(Call<TrackSearchResult> call, retrofit2.Response<TrackSearchResult> response) {
                        trackList.addAll(response.body().getResults());
                    }
                    @Override
                    public void onFailure(Call<TrackSearchResult> call, Throwable t) {
                        Log.e("FAIL", t.getMessage());
                    }
                });
    }

    @Override
    public void loadFavorites(int offset, int limit) {
        // 룸에서 아이디 가져와 검색
    }

//    public void get() {
//        new Thread() {
//            @Override
//            public void run() {
//                api.search("greenday", "song", offset, 20)
//                        .enqueue(new Callback<TrackSearchResult>() {
//                            @Override
//                            public void onResponse(Call<TrackSearchResult> call, retrofit2.Response<TrackSearchResult> response) {
//                                tracks.addAll(response.body().getResults());
//                            }
//                            @Override
//                            public void onFailure(Call<TrackSearchResult> call, Throwable t) {
//                                Log.e("FAIL", t.getMessage());
//                            }
//                        });
//                offset += 20;
//            }
//        }.start();
//
//
//        ArrayList<Integer> ids = new ArrayList<>();
//        ids.add(1068455608);
//        ids.add(317526898);
//        new Thread() {
//            @Override
//            public void run() {
//                api.searchWithTrackId(ids.toString().replace("[", "").replace("]", ""))
//                        .enqueue(new Callback<TrackSearchResult>() {
//                            @Override
//                            public void onResponse(Call<TrackSearchResult> call, retrofit2.Response<TrackSearchResult> response) {
//                                Log.e("CALL", call.request().toString());
//                                for(Track track : response.body().getResults()){
//                                    Log.e("SEARCH", track.getTrackName()+" "+track.getTrackId());
//                                }
//                            }
//                            @Override
//                            public void onFailure(Call<TrackSearchResult> call, Throwable t) {
//                                Log.e("FAIL", t.getMessage());
//                            }
//                        });
//            }
//        }.start();
//    }
}
