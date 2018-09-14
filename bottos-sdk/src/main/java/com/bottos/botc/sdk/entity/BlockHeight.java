package com.bottos.botc.sdk.entity;

import java.math.BigInteger;

/**
 * Created by xionglh on 2018/9/5
 */
public class BlockHeight {

    private long head_block_num;//块号
    private String head_block_hash;//前一块哈希值
    private int head_block_time;//块生成时间
    private String head_block_delegate;//块生产者
    private long cursor_label;//块标签
    private long last_consensus_block_num;//不可逆块号
    private String chain_id;

    public String getChain_id() {
        return chain_id;
    }

    public void setChain_id(String chain_id) {
        this.chain_id = chain_id;
    }

    public long getHead_block_num() {
        return head_block_num;
    }

    public void setHead_block_num(long head_block_num) {
        this.head_block_num = head_block_num;
    }

    public String getHead_block_hash() {
        return head_block_hash;
    }

    public void setHead_block_hash(String head_block_hash) {
        this.head_block_hash = head_block_hash;
    }

    public int getHead_block_time() {
        return head_block_time;
    }

    public void setHead_block_time(int head_block_time) {
        this.head_block_time = head_block_time;
    }

    public String getHead_block_delegate() {
        return head_block_delegate;
    }

    public void setHead_block_delegate(String head_block_delegate) {
        this.head_block_delegate = head_block_delegate;
    }

    public long getCursor_label() {
        return cursor_label;
    }

    public void setCursor_label(long cursor_label) {
        this.cursor_label = cursor_label;
    }

    public long getLast_consensus_block_num() {
        return last_consensus_block_num;
    }

    public void setLast_consensus_block_num(long last_consensus_block_num) {
        this.last_consensus_block_num = last_consensus_block_num;
    }
}
