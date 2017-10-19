import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by root on 8/22/17.
 */
public class CVSplit {
    enum FSM {
        SYN,
        MID,
        AGN,
        FIN
    };
    public static enum TYPE {
        UNKNOW,
        CONSONANT_1,
        CONSONANT_2,
        VOWEL_1,
        VOWEL_2,
        VOWEL_3
    };
     int vowel1_a[] = {
            //a,b,c,d,e,f,g,h,i,j
            1, 0, 0, 0, 3, 0, 0, 0, 4, 0,
            //k,l,m,n,o,p,q,r,s,t
            0, 0, 0, 0, 2, 0, 0, 0, 0, 0,
            //u,v,w,x,y,z
            5, 6, 0, 0, 0, 0
    };
     int vowel2_a[] = {
            //a,b,c, d, e, f, g, h, i, j
            0, 0, 0, 0, 4, 0, 0, 0, 1, 0,
            //k,l,m, n, o, p, q, r, s, t
            0, 0, 0, 6, 2, 0, 0, 5, 0, 0,
            //u,v,w, x, y, z
            3, 0, 0, 0, 0, 0
    };
     int vowel3_a[] = {
            //a,b,c, d, e, f, g, h, i, j
            0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
            //k,l,m,n,o,p,q,r,s,t
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            //u,v,w,x,y,z
            0, 0, 0, 0, 0, 0
    };
     int vowel1[][] = {
            //a,o, e, i, u, v
            {1, 1, 1, 1, 1, 1 } // 0
    };
     int vowel2[][] = {
            //i,o,u, e, r, n
            {2, 2, 0, 0, 0, 2}, //a
            {0, 0, 2, 0, 0, 2}, //o
            {2, 0, 0, 0, 2, 2}, //e
            {0, 0, 2, 2, 0, 2}, //i
            {2, 0, 0, 2, 0, 4}, //u
            {0, 0, 0, 0, 0, 4}  //v
    };
     int vowel3[][] = {
            //g
            {0}, //i
            {0}, //o
            {0}, //u
            {0}, //e
            {0}, //r
            {3}  //n
    };

     int consonant1_a[] = {
            //a, b, c, d, e, f, g, h, i, j
            0, 1, 16, 5, 0, 4, 9, 11, 0, 12,
            //k, l, m, n, o, p, q, r, s, t
            10, 8, 3, 7, 0, 2, 13, 18, 17, 6,
            //u,v, w, x, y, z
            0,  0,20,14,19,15
    };
     int consonant2_a[] = {
            //a, b, c, d, e, f, g, h, i, j
            0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
            //k, l, m, n, o, p, q, r, s, t
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            //u, v, w, x, y, z
            0, 0, 0, 0, 0, 0
    };
     int consonant1[][] = {
            //bp m f d t n l g k h j q x z c s r y w
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1} // 0
    };
     int consonant2[][] = {
            //h
            {0}, // b
            {0}, // p
            {0}, // m
            {0}, // f
            {0}, // d
            {0}, // t
            {0}, // n
            {0}, // l
            {0}, // g
            {0}, // k
            {0}, // h
            {0}, // j
            {0}, // q
            {0}, // x
            {2}, // z
            {2}, // c
            {2}, // s
            {0}, // r
            {0}, // y
            {0}  // w
    };


     int vowel[][][] = {vowel1, vowel2, vowel3};
     int valphabet[][] = {vowel1_a, vowel2_a, vowel3_a};

     int consonant[][][] = {consonant1, consonant2};
     int calphabet[][] = {consonant1_a, consonant2_a};

     int selector[] = {
            //a, b, c, d, e, f, g, h, i, j
            0, 1, 1, 1, 0, 1, 1, 1, 0, 1,
            //k, l, m, n, o, p, q, r, s, t
            1, 1, 1, 1, 0, 1, 1, 1, 1, 1,
            //u, v, w, x, y, z
            0, 0, 1, 1, 1, 1
    };

     ArrayList<CVAttr> split (String pinyin_input, CVInterface imp) {
         ArrayList<CVAttr> result = new ArrayList<>();
         char c;
         String sentence = pinyin_input.toLowerCase() + " ";
         String store = "";
         int accent[][][] = null;
         int alphabet[][] = null;
         int offset;
         int level = 0;
         int last_w = 0;
         int index_r = 0;
         int index_c = 0;
         FSM state = FSM.SYN;
         TYPE assertion = TYPE.UNKNOW;
         for (int i = 0; i < sentence.length(); i++) {
             c = sentence.charAt(i);
             offset = c - 97;

             while (true) {
                 if (state == FSM.SYN) {
                     level = 0;
                     last_w = 0;
                     store = "";
                     if (offset < 0 || offset > 25) {
                         state = FSM.FIN;
                         continue;
                     }
                     accent = selector[offset] == 1 ? consonant : vowel;
                     assertion = selector[offset] == 1 ? TYPE.CONSONANT_1 : TYPE.VOWEL_1;
                     alphabet = selector[offset] == 1 ? calphabet : valphabet;
                     state = FSM.MID;
                 } else if (state == FSM.MID) {
                     if (offset < 0 || offset >25) {
                         state = FSM.FIN;
                         continue;
                     }
                     index_r = store.length() == 0 ? 0 : alphabet[level - 1][store.charAt(store.length() - 1) - 97] - 1;
                     index_c = level < alphabet.length ? alphabet[level][offset] - 1 : index_c;
                     if (level < alphabet.length &&
                             alphabet[level][offset] - 1 >= 0 &&
                             accent[level][index_r][index_c] > last_w) {
                        /*
                        System.out.println(c + " level:" + level +
                                " alphabet len:" + alphabet.length +
                                " alphabet[][]:" + (alphabet[level][offset] - 1) +
                                " state:" + state +
                                " weight:" + accent[level][index_r][index_c] +
                                " accent:" + accent +
                                " last:" + last_w);
                        */
                         last_w = accent[level][index_r][index_c];
                         level++;
                         store += c;
                         break;
                     } else {
                         state = FSM.AGN;
                     }
                 } else if (state == FSM.AGN) {
                     assertion = assertion == TYPE.CONSONANT_1 ?
                             (level == 1 ? TYPE.CONSONANT_1 : TYPE.CONSONANT_2):
                             (level == 1 ? TYPE.VOWEL_1 : (level == 2 ? TYPE.VOWEL_2 : TYPE.VOWEL_3));
                     if (imp != null)
                         imp.callback(store, assertion);
                     result.add(new CVAttr(store, assertion));
                     state = FSM.SYN;
                     continue;
                 } else if (state == FSM.FIN) {
                     state = FSM.SYN;
                     if (assertion == TYPE.UNKNOW)
                         break;
                     assertion = assertion == TYPE.CONSONANT_1 ?
                         (level == 1 ? TYPE.CONSONANT_1 : TYPE.CONSONANT_2):
                         (level == 1 ? TYPE.VOWEL_1 : (level == 2 ? TYPE.VOWEL_2 : TYPE.VOWEL_3));
                     if (imp != null)
                        imp.callback(store, assertion);
                     result.add(new CVAttr(store, assertion));
                     break;
                 } else {
                     //System.out.println("oops! shit error.");
                 }
             }
         }
         return result;
     }



    Queue<String> queue = new LinkedList<>();
    HashSet<String> set = new HashSet<>();

    void init_double_vowel(String accent, TYPE type) {
        if (type == TYPE.VOWEL_1 && queue.size() == 0 && (accent.equals("i") ||accent.equals("u"))||
                (type == TYPE.VOWEL_2 ||
                        type == TYPE.VOWEL_3 ||
                        type == TYPE.VOWEL_1) && queue.size() == 1) {
            queue.add(accent);
        } else {
            if (queue.size() > 1) {
                String y = "";
                for (String x : queue) {
                    //System.out.print(x);
                    y += x;
                }
                //System.out.println(y);
                set.add(y);
            }
            queue.clear();
        }
    };
}
