package com.example.skmall;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Licence_Dialog extends DialogFragment {
    private TextView textView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.licenses_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Licenses");
        builder.setView(view);
        textView = view.findViewById(R.id.textView);
        textView.setText("Licenses are to be added yet\n" + "Career Information\n" +
                "Test debutvs Pakistan at Lord's, Jul 13, 2010Last Testvs New Zealand at Sydney Cricket Ground, Jan 03, 2020ODI debutvs West Indies at Melbourne Cricket Ground, Feb 19, 2010Last ODIvs New Zealand at Sydney Cricket Ground, Mar 13, 2020T20 debutvs Pakistan at Melbourne Cricket Ground, Feb 05, 2010Last T20vs South Africa at Newlands, Feb 26, 2020IPL debutvs Mumbai Indians at Wankhede Stadium, Apr 06, 2012Last IPLvs Royal Challengers Bangalore at M.Chinnaswamy Stadium, Apr 30, 2019ProfileThe best Test batsman at present, Steven Smith's career redemption is a story for the ages. Having made his name initially as a potential leg-spinner who could bat a bit, there was immense criticism, even among those in Australia over the quality of his selection during his early days in international cricket. However, they were made to eat humble pie as the enigmatic man from New South Wales turned things around in sensational fashion to feature among the best batsmen in the world.\n" +
                "\n" +
                "It was the 2007-08 domestic season when Smith made his debut across formats. He was known to give the ball a fair rip and could generate a lot of turn as a leg spinner. His boyish charm and the run up to the crease even drew comparisons with the legendary Shane Warne who had just retired from international cricket a season or so before. Smith's talent was evident but he was too raw for the big stage. In a couple of years, the national call-up came with the limited-overs debut happening in February 2010. The tour of England later that year saw his initiation in the longest format as well.\n" +
                "\n" +
                "Smith started off as a player who could bat at 7 or 8 apart from bowling his leggies. However, there was still a lot of rawness in his skill set. His selection during the Ashes series of 2010-11 drew a lot of contempt and after Australia lost the urn, his career also faded a touch, being dropped from all formats of the game. There was a feeling among the pundits that the bloke had more talent with the bat than with the ball. There was certainly a lot of courage about Smith the batsman and he started working more consciously on it. The former Australian great Greg Chappell once remarked that Smith was the best batting talent in the country. In the coming years, he would be proved right in a big way.\n" +
                "\n" +
                "Fittingly, it was an Ashes series that showcased Smith's resurgence. During the trip of England in 2013, he exhibited a far more improved level of batsmanship, sprinkled with a good dose of grit and mental strength. The unorthodox technique had only grown with an exaggerated trigger movement and shuffle across the stumps, all of which raised eyebrows among the purists. The bottomline though, was that it proved effective and he had an impressive series. Although Australia lost the series, the selectors decided to invest in Smith version 2.0 and the return Ashes series at home that summer saw him continue his upward graph.\n" +
                "\n" +
                "There were a few crisis knocks from him, including the Ashes-sealing hundred at the MCG and he followed it up with an equally impressive tour of South Africa. His contributions were valuable in both those series as Australia emerged victorious. If the 2013-14 season saw Smith making a strong comeback to the international arena, the following season established him among the best in the world. A phenomenal home series against India was the starting point and he hasn't looked back ever since, racking up runs in all conditions against all kinds of attacks. His exploits saw him ranked number one in Tests during the year 2015 and he has remained there since then, by a fairly good margin as well.\n" +
                "\n" +
                "Even as Smith plundered runs at will, there were still a few skeptical opinions on him, about his ability to score in seaming/spinning conditions. The 2015 Ashes tour of England did see him notch up a couple of tons, including a double century at Lord's while the 2017 tour of India displayed him at his impeccable best. Both the series were proof enough that Smith virtually had everything covered in his batting. After England tour in 2015, he was expectedly appointed as Australia's captain for Tests and ODIs while the T20I leadership came over the next six months.\n" +
                "\n" +
                "White-ball cricket has also been a success for Smith although not as much as his Test heroics. In ODIs especially, he has worked himself up the ladder as a premier batsman. The 2015 World Cup was memorable for him as he became the first player to achieve five successive fifty-plus scores in the tournament's history. Those included the quarterfinal, semifinal and final. In T20Is, Smith hasn't quite had the impact that he would have liked to. Missing most of the series hasn't helped either. Nevertheless, his prowess has been on show during the T20 leagues, notably in the IPL where he even led the Pune franchise to the final in 2017.\n" +
                "\n" +
                "It seemed like things were perfect for Smith, runs kept coming especially in Tests but his bargains in ODIs were moderate where Australia were also on a bit of a lean patch. Nevertheless, the reputation that Smith had built up was sensational. However, tragedy struck fiercely on Australia's tour of South Africa in March 2018. The series was strongly contested like most series between the two sides but the on-field controversy ruled the roost with things getting ugly in the third Test at Cape Town. Australia were found guilty of ball tampering that was apparently preplanned as well. As a result Smith along with vice-captain David Warner and rookie Cameron Bancroft faced the boot.\n" +
                "\n" +
                "The ICC slammed a one-Test ban on Smith who was enduring a poor series with the bat as well but the punishment back home from Cricket Australia was more severe - a one year exile from all forms of cricket. The incident saw him being stripped of IPL captaincy for the Royals and consequently, ruled out of the tournament. Having endured a tough one-year ban, Smith returned to competitive cricket in March 2019 through the IPL. Despite seeming a bit rusty, his class was evident and it wasn't surprising when he walked straight back into Australia's World Cup squad.");

        builder.setPositiveButton("GoBack", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create();
    }
}
