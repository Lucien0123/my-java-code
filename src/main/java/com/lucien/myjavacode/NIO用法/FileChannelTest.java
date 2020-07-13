package com.lucien.myjavacode.NIO用法;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;

public class FileChannelTest {

    public static void main(String[] args) {
        String filUrl = "/Users/lucien/test.txt"; // 文件url
        String fileMode = "rw"; // 读写
        try {
            RandomAccessFile aFileAccess = new RandomAccessFile(filUrl, fileMode);
            FileChannel inChannel = aFileAccess.getChannel();

            // 创建一个buffer，每次读/写48个字节
            ByteBuffer buffer = ByteBuffer.allocate(48);
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            charBuffer.put('a');
            int byteRead = inChannel.read(buffer);
            while (byteRead != -1) {
                buffer.flip();  // 从写模式切换为读模式
                while (buffer.hasRemaining()) {

                    System.out.println(buffer.get()); // 每次读一个字节
                }

                buffer.clear(); // 清空buffer，可以写入状态
                // buffer.compact(); // 清空已经读过的buffer内容，可以写入状态
            }
            aFileAccess.close(); // 关闭文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void test1() {
        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open()
                    .bind(new DomainSocketAddress());

            Selector selector = Selector.open();
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            selectionKey.cancel();
            selectionKey.selector();
            selectionKey.readyOps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void test2() {
        try {
            // 创建Pipe
            Pipe pipe = Pipe.open();
            // 向Pipe写数据
            Pipe.SinkChannel sinkChannel = pipe.sink();
            String dataStr = "new string write to sink..." + System.currentTimeMillis();
            ByteBuffer buffer = ByteBuffer.allocate(48);
            buffer.clear();
            buffer.put(dataStr.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                sinkChannel.write(buffer);
            }

            // 从pipe中读取数据
            Pipe.SourceChannel sourceChannel = pipe.source();
            int byteRead = sourceChannel.read(buffer);
            System.out.println("去读的数据长度：" + byteRead);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class DomainSocketAddress extends SocketAddress {

    }
}
