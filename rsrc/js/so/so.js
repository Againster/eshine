
/*
 * return x,y;
 */
function getAbsoluteOffset (e){
    var a = new Array();
    a[0] = $(e).offset().left;
    a[1] = $(e).offset().top;
    return a;
}

/*
 * return x,y
 */
function getRelativeOffset(e) {
    var a = new Array();
    a[0] = $(e).position().left;
    a[1] = $(e).position().top;
    return a;
}

/*
 * return w,h
 */
function getAbsoluteSize(e) {
    var a = new Array();
    a[0] = $(e).outerWidth(true);
    a[1] = $(e).outerHeight(true);
    return a;
}

/*
 * return x,y;
 */
function getTopMidpoint(e) {
    var a = new Array();
    var p = getRelativeOffset(e);
    var s = getAbsoluteSize(e);
    a[0] = p[0] + s[0] / 2;
    a[1] = p[1];
    return a;
}
/*
 * return x,y;
 */
function getBottomMidpoint(e) {
    var a = new Array();
    var p = getRelativeOffset(e);
    var s = getAbsoluteSize(e);
    a[0] = p[0] + s[0] / 2;
    a[1] = p[1] + s[1];
    return a;
}

/*
 * return x,y;
 */
function getLeftMidpoint(e) {
    var a = new Array();
    var p = getRelativeOffset(e);
    var s = getAbsoluteSize(e);
    a[0] = p[0];
    a[1] = p[1] + s[1] / 2;
    return a;
}

/*
 * return x,y;
 */
function getRightMidpoint(e) {
    var a = new Array();
    var p = getRelativeOffset(e);
    var s = getAbsoluteSize(e);
    a[0] = p[0] + s[0];
    a[1] = p[1] + s[1] / 2;
    return a;
}






/*
 * utf16->utf8
 */
function utf16to8(str) {
    var out, i, len, c;

    out = "";
    len = str.length;
    for(i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
        }
    }
    return out;
}

/*
 * utf8->utf16
 */
function utf8to16(str) {
    var out, i, len, c;
    var char2, char3;

    out = "";
    len = str.length;
    i = 0;
    while(i < len) {
        c = str.charCodeAt(i++);
        switch(c >> 4) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
            // 0xxxxxxx
            out += str.charAt(i-1);
            break;
            case 12: case 13:
            // 110x xxxx   10xx xxxx
            char2 = str.charCodeAt(i++);
            out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
            break;
            case 14:
                // 1110 xxxx  10xx xxxx  10xx xxxx
                char2 = str.charCodeAt(i++);
                char3 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x0F) << 12) |
                    ((char2 & 0x3F) << 6) |
                    ((char3 & 0x3F) << 0));
                break;
        }
    }
    return out;
}




/* utf.js - UTF-8 <=> UTF-16 convertion
 *
 * Copyright (C) 1999 Masanao Izumo <iz@onicos.co.jp>
 * Version: 1.0
 * LastModified: Dec 25 1999
 * This library is free.  You can redistribute it and/or modify it.
 */

function Utf8ArrayToStr(array) {
    var out, i, len, c;
    var char2, char3;

    out = "";
    len = array.length;
    i = 0;
    while(i < len) {
        c = array[i++];
        switch(c >> 4)
        {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
            // 0xxxxxxx
            out += String.fromCharCode(c);
            break;
            case 12: case 13:
            // 110x xxxx   10xx xxxx
            char2 = array[i++];
            out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
            break;
            case 14:
                // 1110 xxxx  10xx xxxx  10xx xxxx
                char2 = array[i++];
                char3 = array[i++];
                out += String.fromCharCode(((c & 0x0F) << 12) |
                    ((char2 & 0x3F) << 6) |
                    ((char3 & 0x3F) << 0));
                break;
        }
    }

    return out;
}
