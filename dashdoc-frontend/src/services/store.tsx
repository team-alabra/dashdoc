import { configureStore } from '@reduxjs/toolkit';
import userReducer from '@services/slices/userSlice';
import authReducer from '@services/slices/authSlice';
import planReducer from '@services/slices/planSlice';

// only has 1 key called reducer
// gets passed in as an object
const store = configureStore({
  reducer: {
    user: userReducer,
    auth: authReducer,
    plan: planReducer
    // documents --> single document
    // clients --> single client
    // settings
    // appointments --> single appt
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({ serializableCheck: false })
});

export type RootState = ReturnType<typeof store.getState>;
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;

export default store;
