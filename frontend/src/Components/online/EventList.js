// EventList.js
import React, { useState, useEffect } from 'react';
import FullCalendar from '@fullcalendar/react'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import axios from 'axios';

function EventList() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    fetchEvents();
  }, []);

  // 기본 설정을 포함한 axios 인스턴스 생성
  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8989', // 기본 URL 설정
    headers: {
      'Content-Type': 'application/json', // 기본 요청 본문 타입 설정
      'Authorization': `Bearer ${localStorage.getItem('login_access_token')}` // JWT 토큰 포함
    }
  });

  const fetchEvents = async () => {
    const today = new Date();
    const oneMonthLater = new Date(today);
    oneMonthLater.setMonth(oneMonthLater.getMonth() + 1);

    const formatDate = (date) => {
      return date.toISOString().split('T')[0]; // YYYY-MM-DD 형식
      // return date.toISOString(); // YYYY-MM-DD 형식
    };

    const params = {
      startTime: formatDate(today),
      endTime: formatDate(oneMonthLater),
    };

    try {
      const res = await axiosInstance.post('/online/list', params);
      setEvents(res.data.map(event => ({
        ...event,
        start: event.startTime,
        end: event.endTime
      })));
    } catch (error) {
      console.error("Error fetching events:", error.response?.data || error.message);
      // 여기서 사용자에게 오류 메시지를 표시하거나 다른 처리를 할 수 있습니다.
    }
  };

  const handleDateSelect = async (selectInfo) => {
    const title = prompt('Enter event title:');
    if (title) {
      const event = {
        title,
        startTime: selectInfo.startStr,
        endTime: selectInfo.endStr,
      };
      await axiosInstance.post('/online/events', event);
      fetchEvents();
    }
  };

  return (
    <div className="Event" style={{ height: '100vh' }}>
      <FullCalendar
        plugins={[dayGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        selectable={true}
        select={handleDateSelect}
        events={events}
        height="100%"
        contentHeight="auto"
        aspectRatio={1.35}
      />
    </div>
  );
}

export default EventList;